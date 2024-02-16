package ru.itmo.app.Commands;

import ru.itmo.app.Exceptions.ObjectNotFoundException;
import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;
import ru.itmo.app.Utilities.HumanGetter;
import ru.itmo.app.Managers.ScannerManager;

import java.util.Collections;

/**
 * Class for command 'add_if_min'
 * adds an element if it's lower than the lowest element of the collection
 */
public class AddIfMinCommand extends CollectionCommand {
    public AddIfMinCommand(String name, CollectionManager collectionManager) {
        super(name, collectionManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        try {
            HumanBeing newHuman = new HumanGetter(args, collectionManager, scannerManager).getHuman();
            if (collectionManager.getCollection().isEmpty()) throw new ObjectNotFoundException();
            if (newHuman.compareTo(Collections.min(collectionManager.getCollection())) < 0) {
                collectionManager.getCollection().add(newHuman);
                System.out.println("HumanBeing object was successfully added with ID" + newHuman.getId());
                return;
            }
            System.out.println("HumanBeing object was not added: it's value isn't minimal");
        } catch (ObjectNotFoundException exception) {
            System.out.println("Collection is empty! Nothing to compare");
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
