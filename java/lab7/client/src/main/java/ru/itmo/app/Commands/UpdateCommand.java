package ru.itmo.app.Commands;

import ru.itmo.app.Models.HumanBeing;
import ru.itmo.app.Managers.ClientManager;
import ru.itmo.app.Network.Action;
import ru.itmo.app.Network.CommandRequest;
import ru.itmo.app.Utilities.HumanGetter;
import ru.itmo.app.Managers.ScannerManager;

import java.util.Arrays;

public class UpdateCommand extends ServerCommand {

    public UpdateCommand(String name, ClientManager clientManager) {
        super(name, clientManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        try {
            HumanBeing newHuman = new HumanGetter(Arrays.copyOfRange(args, 1, args.length), scannerManager).getHuman();
           send(Action.COMMAND, getName(), newHuman, Arrays.copyOfRange(args, 0, 1));
        } catch (IllegalArgumentException exception) {
            System.out.println("Incorrect arguments. Must be: update [id] [name] [realHero] [hasToothPick] [impactSpeed].");
        }
    }

    @Override
    public int numOfArgs() {
        return -1;
    }

    @Override
    public String getDescription() {
        return "updates element's fields, which ID equals to the given";
    }
}
