package ru.itmo.app.Commands;

import ru.itmo.app.Managers.ScannerManager;
import ru.itmo.app.Managers.ClientManager;
import ru.itmo.app.Network.Action;
import ru.itmo.app.Network.CommandRequest;

/**
 * Class for command 'average_of_impact_speed'
 * displays average value of impactSpeed field of all elements of the collection
 */
public class AvgOfSpeedCommand extends ServerCommand {
    public AvgOfSpeedCommand(String name, ClientManager clientManager) {
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
        return "displays the average value of the impactSpeed field of all elements of the collection";
    }
}
