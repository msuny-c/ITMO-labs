package ru.itmo.app.Commands;

import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Managers.ScannerManager;

/**
 * Class for command 'reorder'
 * reverses current order of elements in the collection.
 */
public class ReorderCommand extends CollectionCommand {
    public ReorderCommand(String name, CollectionManager collectionManager) {
        super(name, collectionManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        var collection = collectionManager.getCollection();
        for (int i = 0; i < collection.size() - 1; i++)
            collection.insertElementAt(collection.pop(), i);
    }

    @Override
    public int numOfArgs() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "reverses current order of elements";
    }
}
