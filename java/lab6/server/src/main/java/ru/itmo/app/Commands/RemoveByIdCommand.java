package ru.itmo.app.Commands;

import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;

import java.util.NoSuchElementException;

public class RemoveByIdCommand extends AbstractCommand {
    public RemoveByIdCommand(String name) {
        super(name);
    }

    @Override
    public String execute(CollectionManager collectionManager, HumanBeing object, String[] args) {
        try {
            int id = Integer.parseInt(args[0]);
            HumanBeing objectToRemove = collectionManager.getCollection().stream().filter(o -> o.getId().equals(id)).findFirst().get();
            collectionManager.getCollection().remove(objectToRemove);
            return String.format("Object with id %s was successfully removed.", objectToRemove.getId());
        } catch (NoSuchElementException exception) {
            return "Object with given id was not found.";
        }
    }

    @Override
    public int numOfArgs() {
        return 0;
    }
}
