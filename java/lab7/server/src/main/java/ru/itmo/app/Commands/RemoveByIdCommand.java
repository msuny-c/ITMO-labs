package ru.itmo.app.Commands;

import ru.itmo.app.Exceptions.AuthException;
import ru.itmo.app.Exceptions.NotPermissionException;
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
            collectionManager.remove(id);
            return String.format("Object with id %s was successfully removed.", id);
        } catch (NoSuchElementException exception) {
            return "Object with given id was not found.";
        } catch (NotPermissionException exception) {
            return "You do not have enough rights to delete this object.";
        }
    }

    @Override
    public int numOfArgs() {
        return 0;
    }
}
