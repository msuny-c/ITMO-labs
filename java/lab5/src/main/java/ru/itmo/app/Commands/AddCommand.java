package ru.itmo.app.Commands;

import ru.itmo.app.Managers.*;
import ru.itmo.app.Models.HumanBeing;
import ru.itmo.app.Utilities.HumanGetter;
import ru.itmo.app.Managers.ScannerManager;

/**
 * Class for command 'add'.
 * adds new element to the collection.
 */
public class AddCommand extends CollectionCommand {
    public AddCommand(String name, CollectionManager collectionManager) {
        super(name, collectionManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        try {
            HumanBeing newHuman = new HumanGetter(args, collectionManager, scannerManager).getHuman();
            collectionManager.getCollection().add(newHuman);
            System.out.println("HumanBeing object was successfully added with ID " + newHuman.getId());
        } catch (IllegalArgumentException exception) {
            System.out.println("Incorrect arguments. Should be: add {name} {realHero} {hasToothPick} {impactSpeed}");
        }
    }

    @Override
    public int numOfArgs() {
        return -1;
    }

    @Override
    public String getDescription() {
        return "adds new element to the collection";
    }
}
