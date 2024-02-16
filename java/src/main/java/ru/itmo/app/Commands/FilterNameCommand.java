package ru.itmo.app.Commands;

import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;
import ru.itmo.app.Managers.ScannerManager;

/**
 * Class for command 'filter_starts_with_name'
 * displays an elements which name starts with a specified substring.
 */
public class FilterNameCommand extends CollectionCommand {
    public FilterNameCommand(String name, CollectionManager collectionManager) {
        super(name, collectionManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        if (args.length == 0) throw new IllegalArgumentException();
        var collection = collectionManager.getCollection();
        for (HumanBeing object : collection) {
            if (object.getName().startsWith(String.join(" ", args))) System.out.println(object);
            return;
        }
        System.out.println("There's no objects which name starts with specified substring");
    }

    @Override
    public int numOfArgs() {
        return -1;
    }

    @Override
    public String getDescription() {
        return "displays elements whose name field values begin with a specified substring";
    }
}
