package ru.itmo.app.Commands;

import ru.itmo.app.Managers.ScannerManager;
import ru.itmo.app.Managers.ClientManager;
import ru.itmo.app.Network.Action;
import ru.itmo.app.Network.CommandRequest;

/**
 * Class for command 'remove_by_id'
 * removes an element from the collection by a specified id.
 */
public class RemoveByIdCommand extends ServerCommand {

    public RemoveByIdCommand(String name, ClientManager clientManager) {
        super(name, clientManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        try {
            Integer.parseInt(args[0]);
            send(Action.COMMAND, getName(), null, args);
        } catch (IllegalArgumentException exception) {
            System.out.println("Incorrect arguments. Should be: remove_by_id {id}");
        }
    }

    @Override
    public int numOfArgs() {
        return 1;
    }

    @Override
    public String getDescription() {
        return "removes element by given ID";
    }
}
