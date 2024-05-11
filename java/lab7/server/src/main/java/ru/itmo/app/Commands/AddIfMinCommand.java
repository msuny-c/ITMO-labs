package ru.itmo.app.Commands;

import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;

import java.util.Collections;
import java.util.NoSuchElementException;

public class AddIfMinCommand extends AbstractCommand {
    public AddIfMinCommand(String name) {
        super(name);
    }

    @Override
    public String execute(CollectionManager collectionManager, HumanBeing object, String[] args) {
        try {
            boolean success = collectionManager.addIfMin(object);
            if (success) {
                return "Object was successfully added to the collection.";
            }
            return "HumanBeing object was not added it's not minimum.";
        } catch (NoSuchElementException exception) {
            return "Collection is empty. Nothing to compare.";
        }
    }

    @Override
    public int numOfArgs() {
        return 0;
    }
}
