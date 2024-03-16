package ru.itmo.app.Commands;

import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;

public class ClearCommand extends AbstractCommand {
    public ClearCommand(String name) {
        super(name);
    }

    @Override
    public String execute(CollectionManager collectionManager, HumanBeing object, String[] args) {
        collectionManager.getCollection().clear();
        return "Collection was cleared successfully.";
    }

    @Override
    public int numOfArgs() {
        return 0;
    }

    @Override
    public String getName() {
        return "clear";
    }
}
