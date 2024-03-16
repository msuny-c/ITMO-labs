package ru.itmo.app.Commands;

import ru.itmo.app.Utilities.HumanSetter;
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
        collectionManager.getCollection().add(object);
        HumanSetter.setId(object, collectionManager);
        HumanSetter.setCreationDate(object);
        return "Object was successfully added with id " + object.getId() + ".";
    }

    @Override
    public int numOfArgs() {
        return 0;
    }
}
