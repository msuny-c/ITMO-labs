package ru.itmo.app.Commands;

import ru.itmo.app.Managers.ScannerManager;
import ru.itmo.app.Models.HumanBeing;
import ru.itmo.app.Managers.ClientManager;
import ru.itmo.app.Network.Action;
import ru.itmo.app.Network.CommandRequest;
import ru.itmo.app.Utilities.HumanGetter;

/**
 * Class for command 'add_if_min'
 * adds an element if it's lower than the lowest element of the collection
 */
public class AddIfMinCommand extends ServerCommand {
    public AddIfMinCommand(String name, ClientManager clientManager) {
        super(name, clientManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        try {
            HumanBeing newHuman = new HumanGetter(args, scannerManager).getHuman();
            send(Action.COMMAND, getName(), newHuman, null);
        } catch (IllegalArgumentException exception) {
            System.out.println("Incorrect arguments. Should be: add_if_min {name} {realHero} {hasToothPick} {impactSpeed}");
        }
    }

    @Override
    public int numOfArgs() {
        return -1;
    }

    @Override
    public String getDescription() {
        return "adds an element to the collection if it is less than the smallest element of the collection";
    }
}
