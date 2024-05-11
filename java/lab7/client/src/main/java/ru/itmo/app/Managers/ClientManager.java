package ru.itmo.app.Managers;

import ru.itmo.app.Network.CommandRequest;
import ru.itmo.app.Network.Response;
import ru.itmo.app.Network.Status;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;

public class ClientManager {
    private final InetAddress address;
    private final Integer port;
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private boolean isConnected = true;
    private final byte[] data = new byte[4096];
    public Status messageStatus;
    public ClientManager(InetAddress address, Integer port) {
        this.address = address;
        this.port = port;
    }
    public void connect(InetAddress address, Integer port) {
        try {
            socket = new Socket(address, port);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            System.out.println("The connection to the server was successfully established.");
        } catch (IOException exception) {
            System.out.println("The connection to the server was not established.");
            isConnected = false;
        }
    }
    public void send(CommandRequest request) {
        if (!isConnected) {
            try {
                socket = new Socket(address, port);
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
            } catch (IOException ignored) {}
        }
        try {
            var serialized = serialize(request);
            outputStream.write(header(serialized));
            outputStream.write(serialized);
            var received = inputStream.read(data);
            if (received == -1) {
                System.out.println("The connection to the server is not established.");
                isConnected = false;
                return;
            }
            Response response = deserialize(data);
            handle(response);
            isConnected = true;
        } catch (SocketException | NullPointerException exception) {
            System.out.println("The connection to the server is not established.");
            isConnected = false;
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.exit(-1);
        }
    }
    private byte[] serialize(CommandRequest request) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(request);
        return bos.toByteArray();
    }
    private Response deserialize(byte[] array) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteOutputStream = new ByteArrayInputStream(array);
        ObjectInputStream ois = new ObjectInputStream(byteOutputStream);
        return (Response) ois.readObject();
    }
    private byte[] header(byte[] request) {
        return ByteBuffer.allocate(4).putInt(request.length).array();
    }
    private void handle(Response response) {
        if (response.error() != null && response.error().hasError()) {
            response.error().printError();
            System.exit(-1);
        } else {
            messageStatus = response.status();
            System.out.println(response.message());
        }
    }
}
