package ru.itmo.app.Commands;
import ru.itmo.app.Managers.*;
import ru.itmo.app.Managers.ClientManager;
import ru.itmo.app.Network.CommandRequest;

/**
 * Class for command 'show'
 * displays collection in a string representation.
 */
public class ShowCommand extends ServerCommand {
    public ShowCommand(String name, ClientManager clientManager) {
        super(name, clientManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        CommandRequest request = new CommandRequest(getName(), null, null);
        send(request);
    }

    @Override
    public int numOfArgs() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "displays collection in a string representation";
    }

}
