package ru.itmo.app.Network;

import java.net.SocketAddress;
import java.nio.ByteBuffer;

public class ClientData {
    public ByteBuffer buffer = ByteBuffer.allocate(4096);
    public int PACKET_SIZE;
    public final SocketAddress IP_ADDRESS;
    public ClientData(SocketAddress IP_ADDRESS) {
        this.IP_ADDRESS = IP_ADDRESS;
    }
}
