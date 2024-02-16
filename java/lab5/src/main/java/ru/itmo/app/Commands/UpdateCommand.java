package ru.itmo.app.Commands;
import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;
import ru.itmo.app.Utilities.HumanGetter;
import ru.itmo.app.Managers.ScannerManager;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class UpdateCommand extends CollectionCommand {

    public UpdateCommand(String name, CollectionManager collectionManager) {
        super(name, collectionManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        try {
            HumanBeing objectToUpdate = collectionManager.getCollection().stream().filter(o -> o.getId() == Integer.parseInt(args[0])).findFirst().get();
            objectToUpdate.update(new HumanGetter(Arrays.copyOfRange(args, 1, args.length), collectionManager, scannerManager).getHuman());
            System.out.printf("Object with ID %s was successfully updated\n", objectToUpdate.getId());
        } catch (NoSuchElementException exception) {
            System.out.println("Object with given ID was not found");
        }
    }

    @Override
    public int numOfArgs() {
        return -1;
    }

    @Override
    public String getDescription() {
        return "updates element's fields, which ID equals to the given";
    }
}
