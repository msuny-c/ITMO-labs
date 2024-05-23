package ru.itmo.app.Managers;

import ru.itmo.app.Exceptions.ServerException;
import ru.itmo.app.Interfaces.ICommandHandler;
import ru.itmo.app.Interfaces.IServerManager;
import ru.itmo.app.Network.*;

import java.io.*;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import ru.itmo.app.Network.Error;
import ru.itmo.app.Network.CommandRequest;
import ru.itmo.app.Network.Response;
import ru.itmo.app.Network.Status;

public class ServerManager implements IServerManager {
    private final int HEADER = 4;
    private final ConcurrentHashMap<SocketChannel, ClientData> session = new ConcurrentHashMap<>();
    private final Integer port;
    private final ICommandHandler commandHandler;
    private final ForkJoinPool forkJoinPool = new ForkJoinPool();
    private final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(8);
    private final Set<Future<?>> futures = new HashSet<>();
    private static final Logger logger = LoggerFactory.getLogger(ServerManager.class);

    public ServerManager(Integer port, ICommandHandler commandHandler) {
        this.port = port;
        this.commandHandler = commandHandler;
    }

    public void run() throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), port));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        logger.info("Server is running on port " + port);
        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            for (var iter = keys.iterator(); iter.hasNext(); ) {
                SelectionKey key = iter.next();
                iter.remove();
                if (!key.isValid()) continue;
                if (key.isAcceptable()) register(key);
                else if (key.isReadable()) {
                    try {
                        fixedThreadPool.submit(() -> {
                            try {
                                read(key);
                            } catch (IOException exception) {
                                logger.error(exception.getMessage());
                            }
                        }).get();
                    } catch (ExecutionException | InterruptedException exception) {
                        logger.error(exception.getMessage());
                    }
                }
                else if (key.isWritable()) {
                    forkJoinPool.submit(() -> handleRequest(key)).join();
                }
            }
        }
    }

    private void register(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel client = serverSocketChannel.accept();
        client.configureBlocking(false);
        client.register(key.selector(), SelectionKey.OP_READ);
        session.put(client, new ClientData(client.getRemoteAddress()));
        logger.info("Client " + client.getRemoteAddress() + " connected.");
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        ClientData data = session.get(client);
        try {
            var received = client.read(data.buffer);
            if (received == -1) {
                disconnect(key);
                return;
            }
            if (received != 0) {
                logger.info("Received " + received + " bytes from " + data.IP_ADDRESS + ".");
            }
        } catch (SocketException exception) {
            disconnect(key);
            return;
        }
        if (data.PACKET_SIZE == 0 && data.buffer.position() >= HEADER) {
            data.PACKET_SIZE = data.buffer.getInt(0);
        }
        if (data.buffer.position() >= data.PACKET_SIZE + HEADER) {
            client.register(key.selector(), SelectionKey.OP_WRITE);
        }
    }

    private void handleRequest(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();
        var data = session.get(client);
        byte[] packet = new byte[data.PACKET_SIZE];
        data.buffer.flip();
        data.buffer.position(HEADER);
        data.buffer.get(packet, 0, data.PACKET_SIZE);
        try {
            CommandRequest request = deserialize(packet);
            Response response = commandHandler.handle(request);
            fixedThreadPool.submit(() -> send(response, key)).get();
            if (key.isValid()) {
                client.register(key.selector(), SelectionKey.OP_READ);
            }
        } catch (SocketException exception) {
            disconnect(key);
        } catch (IOException | ExecutionException | InterruptedException | ClassNotFoundException exception) {
            logger.error(exception.getMessage());
        }
    }
    private void send(Response response, SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();
        var data = session.get(client);
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(byteStream);
            oos.writeObject(response);
        } catch (IOException exception) {
            logger.error(exception.getMessage());
        }
        try {
            int sent = client.write(ByteBuffer.wrap(byteStream.toByteArray()));
            logger.info("Sent " + sent + " bytes to " + data.IP_ADDRESS + ".");
            data.PACKET_SIZE = 0;
            data.buffer.compact();
        } catch (SocketException exception) {
            disconnect(key);
        } catch (IOException exception) {
            logger.error(exception.getMessage());
        }
    }

    private CommandRequest deserialize(byte[] array) throws ClassNotFoundException, IOException {
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(array));
        return (CommandRequest) ois.readObject();
    }


    private void disconnect(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();
        logger.info("Client " + session.get(client).IP_ADDRESS + " disconnected.");
        session.remove(client);
        key.cancel();
    }
}
