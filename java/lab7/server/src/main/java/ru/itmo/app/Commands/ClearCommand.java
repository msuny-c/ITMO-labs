package ru.itmo.app.Commands;

import ru.itmo.app.Exceptions.NotPermissionException;
import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;

public class ClearCommand extends AbstractCommand {
    public ClearCommand(String name) {
        super(name);
    }

    @Override
    public String execute(CollectionManager collectionManager, HumanBeing object, String[] args) {
        try {
            collectionManager.clear();
            return "Your objects have been successfully deleted.";
        } catch (NotPermissionException exception) {
            return "There are no objects you created in the collection.";
        }
    }

    @Override
    public int numOfArgs() {
        return 0;
    }
}
