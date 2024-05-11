package ru.itmo.app.Commands;

import ru.itmo.app.Exceptions.UserException;
import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;

import java.util.Objects;

public class AddCommand extends AbstractCommand {
    public AddCommand(String name) {
        super(name);
    }

    @Override
    public String execute(CollectionManager collectionManager, HumanBeing object, String[] args) throws UserException {
        Objects.requireNonNull(object);
        boolean success = collectionManager.add(object);
        if (success) {
            return "Object was successfully added with id " + object.id() + ".";
        }
        return "You do not have enough rights to add this object";
    }

    @Override
    public int numOfArgs() {
        return 0;
    }
}
