package ru.itmo.app.Commands;

import ru.itmo.app.Exceptions.AuthException;
import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;

import java.util.Objects;

public class AddCommand extends AbstractCommand {
    public AddCommand(String name) {
        super(name);
    }

    @Override
    public String execute(CollectionManager collectionManager, HumanBeing object, String[] args) {
        Objects.requireNonNull(object);
        collectionManager.add(object);
        return "Object was successfully added with id " + object.id() + ".";
    }

    @Override
    public int numOfArgs() {
        return 0;
    }
}
