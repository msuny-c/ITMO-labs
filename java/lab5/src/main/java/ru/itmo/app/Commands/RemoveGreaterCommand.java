package ru.itmo.app.Commands;

import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;
import ru.itmo.app.Utilities.HumanGetter;
import ru.itmo.app.Managers.ScannerManager;

/**
 * Class for command 'remove_greater'
 * removes all elements from the collection larger than the specified element.
 */
public class RemoveGreaterCommand extends CollectionCommand {
    public RemoveGreaterCommand(String name, CollectionManager collectionManager) {
        super(name, collectionManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        HumanBeing newHuman = new HumanGetter(args, collectionManager, scannerManager).getHuman();
        collectionManager.getCollection().removeIf(object -> object.compareTo(newHuman) > 0);
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
