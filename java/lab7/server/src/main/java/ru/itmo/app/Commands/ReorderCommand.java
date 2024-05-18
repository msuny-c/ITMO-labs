package ru.itmo.app.Commands;

import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;

public class ReorderCommand extends AbstractCommand {

    public ReorderCommand(String name) {
        super(name);
    }

    @Override
    public String execute(CollectionManager collectionManager, HumanBeing object, String[] args) {
        collectionManager.reorder();
        return "Collection was reordered successfully.";
    }

    @Override
    public int numOfArgs() {
        return 0;
    }
}
