package ru.itmo.app.Commands;
import ru.itmo.app.Managers.*;
import ru.itmo.app.Managers.ScannerManager;

public class ClearCommand extends CollectionCommand {
    public ClearCommand(String name, CollectionManager collectionManager) {
        super(name, collectionManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        collectionManager.getCollection().clear();
    }

    @Override
    public int numOfArgs() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "clears the collection";
    }
}
