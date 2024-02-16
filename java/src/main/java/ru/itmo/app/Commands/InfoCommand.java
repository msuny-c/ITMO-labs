package ru.itmo.app.Commands;
import ru.itmo.app.Managers.*;
import ru.itmo.app.Managers.ScannerManager;

/**
 * Class for command 'info'
 * displays an information about the collection;
 */
public class InfoCommand extends CollectionCommand {

    public InfoCommand(String name, CollectionManager collectionManager) {
        super(name, collectionManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        System.out.printf("Information about collection:\n" +
                "\tCollection type: %s\n" + "\tInitialization date: %s\n" + "\tCollection size: %s%n",
                collectionManager.TYPE, collectionManager.getInitTime(), collectionManager.getCollection().size());
    }

    @Override
    public int numOfArgs() {
        return 0;
    }

    @Override
    public String getDescription() {

        return "displays an information about the collection";
    }
}
