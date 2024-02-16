package ru.itmo.app.Commands;
import ru.itmo.app.Managers.*;

/**
 * Class for command 'show'
 * displays collection in a string representation.
 */
public class ShowCommand extends CollectionCommand {
    public ShowCommand(String name, CollectionManager collectionManager) {
        super(name, collectionManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        System.out.println(collectionManager.getCollection());
    }

    @Override
    public int numOfArgs() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "displays collection in a string representation";
    }

}
