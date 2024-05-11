package ru.itmo.app.Commands;

import ru.itmo.app.Managers.ScannerManager;
import ru.itmo.app.Models.HumanBeing;
import ru.itmo.app.Managers.ClientManager;
import ru.itmo.app.Network.Action;
import ru.itmo.app.Network.CommandRequest;
import ru.itmo.app.Utilities.HumanGetter;

/**
 * Class for command 'add'.
 * adds new element to the collection.
 */
public class AddCommand extends ServerCommand {
    public AddCommand(String name, ClientManager clientManager) {
        super(name, clientManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        try {
            HumanBeing newHuman = new HumanGetter(args, scannerManager).getHuman();
           send(Action.COMMAND, getName(), newHuman, null);
        } catch (IllegalArgumentException exception) {
            System.out.println("Incorrect arguments. Must be: add [name] [realHero] [hasToothPick] [impactSpeed].");
        }
    }

    @Override
    public int numOfArgs() {
        return -1;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "adds new element to the collection";
    }
}
