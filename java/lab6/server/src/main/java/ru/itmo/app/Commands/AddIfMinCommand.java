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
            if (collectionManager.getCollection().isEmpty()) throw new NoSuchElementException();
            if (object.compareTo(Collections.min(collectionManager.getCollection())) < 0) {
                collectionManager.getCollection().add(object);
                return "HumanBeing object was successfully added with id" + object.getId() + ".";
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
