package ru.itmo.app.Commands;

import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;

import java.util.Objects;

public class RemoveGreaterCommand extends AbstractCommand{
    public RemoveGreaterCommand(String name) {
        super(name);
    }

    @Override
    public String execute(CollectionManager collectionManager, HumanBeing object, String[] arg) {
        Objects.requireNonNull(object);
        collectionManager.removeGreater(object);
        return "Objects greater than given was successfully removed.";
    }

    @Override
    public int numOfArgs() {
        return 0;
    }
}
