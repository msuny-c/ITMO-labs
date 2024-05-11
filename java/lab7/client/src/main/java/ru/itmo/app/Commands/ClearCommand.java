package ru.itmo.app.Commands;

import ru.itmo.app.Managers.ScannerManager;
import ru.itmo.app.Managers.ClientManager;
import ru.itmo.app.Network.Action;
import ru.itmo.app.Network.CommandRequest;

public class ClearCommand extends ServerCommand{

    public ClearCommand(String name, ClientManager clientManager) {
        super(name, clientManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        send(Action.COMMAND, getName(), null,  null);
    }

    @Override
    public int numOfArgs() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "clears collection";
    }
}
