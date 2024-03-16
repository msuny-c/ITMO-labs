package ru.itmo.app.Commands;

import ru.itmo.app.Managers.ClientManager;
import ru.itmo.app.Network.CommandRequest;

public abstract class ServerCommand extends AbstractCommand {
    private final ClientManager clientManager;
    public ServerCommand(String name, ClientManager clientManager) {
        super(name);
        this.clientManager = clientManager;
    }
    public void send(CommandRequest request) {
        clientManager.send(request);
    }
}
