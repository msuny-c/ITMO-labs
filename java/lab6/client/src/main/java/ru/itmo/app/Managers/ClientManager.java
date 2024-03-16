package ru.itmo.app.Managers;

import ru.itmo.app.Network.CommandRequest;
import ru.itmo.app.Network.Response;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;

public class ClientManager {
    private final int ATTEMPTS = 5;
    private final InetAddress address;
    private final Integer port;
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    public ClientManager(InetAddress address, Integer port) {
        this.address = address;
        this.port = port;
    }
    public boolean connect(InetAddress address, Integer port) {
        try {
            socket = new Socket(address, port);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            System.out.println("Connected to server successfully.");
            return true;
        } catch (IOException exception) {
            System.out.println("Error while connecting to the server.");
            return false;
        }
    }
    private boolean reconnect() {
        for (int i = 1; i < 5 + 1; i++) {
            System.out.println("Connecting attempt... " + i);
            if (connect(address, port)) return true;
        }
        System.out.println("Failed to connect to the server.");
        return false;
    }
    public void send(CommandRequest request) {
        if (socket == null) {
            System.out.println("Server communication error.");
            if (reconnect()) {
                System.out.println("Connected to the server. Resending request...");
                send(request);
                return;
            }
            System.out.println("Failed to establish connection.");
            return;
        }
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(byteStream);
            oos.writeObject(request);
            var header = ByteBuffer.allocate(4).putInt(byteStream.size()).array();
            outputStream.write(header);
            outputStream.write(byteStream.toByteArray());
            byte[] data = new byte[4096];
            inputStream.read(data);
            ByteArrayInputStream byteOutputStream = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(byteOutputStream);
            Response response = (Response) ois.readObject();
            if (response.error().hasError()) {
                response.error().printError();
                System.exit(-1);
            } else System.out.println(response.message());
        } catch (SocketException exception) {
            System.out.println("Server communication error.");
            if (reconnect()) {
                System.out.println("Connected to the server. Resending request...");
                send(request);
            }
            System.out.println("Failed to establish connection.");
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

}
