package ru.itmo.app.Commands;

import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;

public class ClearCommand extends AbstractCommand {
    public ClearCommand(String name) {
        super(name);
    }

    @Override
    public String execute(CollectionManager collectionManager, HumanBeing object, String[] args) {
        boolean cleared = collectionManager.clear();
        if (cleared) {
            return "Your objects have been successfully deleted.";
        }
        return "There are no objects you created in the collection.";
    }

    @Override
    public int numOfArgs() {
        return 0;
    }
}
