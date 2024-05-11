package ru.itmo.app.Commands;

import ru.itmo.app.Models.HumanBeing;
import ru.itmo.app.Managers.ClientManager;
import ru.itmo.app.Network.Action;
import ru.itmo.app.Network.CommandRequest;
import ru.itmo.app.Utilities.HumanGetter;
import ru.itmo.app.Managers.ScannerManager;

/**
 * Class for command 'remove_greater'
 * removes all elements from the collection larger than the specified element.
 */
public class RemoveGreaterCommand extends ServerCommand {
    public RemoveGreaterCommand(String name, ClientManager clientManager) {
        super(name, clientManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        try {
            HumanBeing newHuman = new HumanGetter(args, scannerManager).getHuman();
            send(Action.COMMAND, getName(), newHuman, args);
        } catch (IllegalArgumentException exception) {
            System.out.println("Incorrect arguments. Should be: remove_greater {name} {realHero} {hasToothPick} {impactSpeed}");
        }
    }

    @Override
    public int numOfArgs() {
        return -1;
    }

    @Override
    public String getDescription() {
        return "removes all elements from the collection larger than the specified element";
    }
}
