package ru.itmo.app.Commands;

import ru.itmo.app.Managers.ClientManager;
import ru.itmo.app.Models.HumanBeing;
import ru.itmo.app.Network.Action;
import ru.itmo.app.Network.CommandRequest;
import ru.itmo.app.Network.Session;
import ru.itmo.app.Network.Status;

public abstract class ServerCommand extends AbstractCommand {
    private final ClientManager clientManager;
    public ServerCommand(String name, ClientManager clientManager) {
        super(name);
        this.clientManager = clientManager;
    }
    protected Status send(Action action, String command, HumanBeing object, String[] args) {
        CommandRequest request = new CommandRequest(action, command, object, args, Session.user.user, Session.user.password);
        clientManager.send(request);
        return clientManager.messageStatus;
    }
}
