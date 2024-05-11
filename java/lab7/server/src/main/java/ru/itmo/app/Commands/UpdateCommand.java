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
            Integer id = Integer.parseInt(args[0]);
            boolean success = collectionManager.update(id, object);
            if (success) {
                return String.format("Object with ID %s was successfully updated.", id);
            }
            return "You do not have enough rights to modify this object.";
        } catch (NoSuchElementException exception) {
            return "Object with given ID was not found.";
        }
    }

    @Override
    public int numOfArgs() {
        return 0;
    }
}
