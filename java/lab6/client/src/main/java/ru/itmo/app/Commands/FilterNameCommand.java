package ru.itmo.app.Commands;

import ru.itmo.app.Managers.ScannerManager;
import ru.itmo.app.Managers.ClientManager;
import ru.itmo.app.Network.CommandRequest;

/**
 * Class for command 'filter_starts_with_name'
 * displays an elements which name starts with a specified substring.
 */
public class FilterNameCommand extends ServerCommand {
    public FilterNameCommand(String name, ClientManager clientManager) {
        super(name, clientManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        try {
            if (args.length == 0) throw new IllegalArgumentException();
            CommandRequest request = new CommandRequest("filter_starts_with_name", null, args);
            send(request);
        } catch (IllegalArgumentException exception) {
            System.out.println("Incorrect arguments. Should be: filter_starts_with_name {sub}");
        }
    }

    @Override
    public int numOfArgs() {
        return -1;
    }

    @Override
    public String getDescription() {
        return "displays elements whose name field values begin with a specified substring";
    }
}
