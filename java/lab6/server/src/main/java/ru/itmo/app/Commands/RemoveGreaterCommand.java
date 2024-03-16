package ru.itmo.app.Commands;

import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;

import java.util.Objects;

public class RemoveGreaterCommand extends AbstractCommand{
    public RemoveGreaterCommand(String name) {
        super(name);
    }

    @Override
    public String execute(CollectionManager collectionManager, HumanBeing object, String[] args) {
        Objects.requireNonNull(object);
        if (collectionManager.getCollection().isEmpty()) return "Collection is empty. Nothing to compare.";
        collectionManager.getCollection().removeIf(human -> human.compareTo(object) > 0);
        return "Objects greater than given was successfully removed.";
    }

    @Override
    public int numOfArgs() {
        return 0;
    }
}
