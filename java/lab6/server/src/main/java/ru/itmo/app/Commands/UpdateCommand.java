package ru.itmo.app.Commands;

import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;

import java.util.NoSuchElementException;

public class UpdateCommand extends AbstractCommand {
    public UpdateCommand(String name) {
        super(name);
    }

    @Override
    public String execute(CollectionManager collectionManager, HumanBeing object, String[] args) {
        try {
            HumanBeing objectToUpdate = collectionManager.getCollection().stream().filter(o -> o.getId() == Integer.parseInt(args[0])).findFirst().get();
            objectToUpdate.update(object);
            return String.format("Object with ID %s was successfully updated", objectToUpdate.getId());
        } catch (NoSuchElementException exception) {
            return "Object with given ID was not found.";
        }
    }

    @Override
    public int numOfArgs() {
        return 0;
    }
}
