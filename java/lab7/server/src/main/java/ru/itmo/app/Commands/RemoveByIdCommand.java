package ru.itmo.app.Commands;

import ru.itmo.app.Exceptions.UserException;
import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;

import java.util.NoSuchElementException;

public class RemoveByIdCommand extends AbstractCommand {
    public RemoveByIdCommand(String name) {
        super(name);
    }

    @Override
    public String execute(CollectionManager collectionManager, HumanBeing object, String[] args) throws UserException {
        try {
            int id = Integer.parseInt(args[0]);
            boolean success = collectionManager.remove(id);
            if (success) {
                return String.format("Object with id %s was successfully removed.", id);
            }
            return "You do not have enough rights to delete this object.";
        } catch (NoSuchElementException exception) {
            return "Object with given id was not found.";
        }
    }

    @Override
    public int numOfArgs() {
        return 0;
    }
}
