package ru.itmo.app.Commands;
import ru.itmo.app.Managers.ScannerManager;
import ru.itmo.app.Managers.ClientManager;
import ru.itmo.app.Network.Action;
import ru.itmo.app.Network.CommandRequest;

/**
 * Class for command 'info'
 * displays an information about the collection;
 */
public class InfoCommand extends ServerCommand {

    public InfoCommand(String name, ClientManager clientManager) {
        super(name, clientManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        send(Action.COMMAND, getName(), null, null);
    }

    @Override
    public int numOfArgs() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "displays an information about the collection";
    }
}
