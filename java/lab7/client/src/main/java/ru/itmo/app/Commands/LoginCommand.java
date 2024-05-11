package ru.itmo.app.Commands;

import ru.itmo.app.Managers.ClientManager;
import ru.itmo.app.Managers.ScannerManager;
import ru.itmo.app.Network.Action;
import ru.itmo.app.Network.Session;
import ru.itmo.app.Network.Status;
import ru.itmo.app.Network.User;

public class LoginCommand extends ServerCommand {
    public LoginCommand(String name, ClientManager clientManager) {
        super(name, clientManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        User previousUser = Session.user;
        Session.setUser(new User(args[0], args[1]));
        Status status = send(Action.LOGIN, null, null, null);
        if (status == Status.FAIL) {
            Session.setUser(previousUser);
        }

    }

    @Override
    public int numOfArgs() {
        return 2;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
