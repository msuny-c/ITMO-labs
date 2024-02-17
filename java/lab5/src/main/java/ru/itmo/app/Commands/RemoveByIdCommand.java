package ru.itmo.app.Commands;

import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;
import ru.itmo.app.Managers.ScannerManager;
import java.util.NoSuchElementException;

/**
 * Class for command 'remove_by_id'
 * removes an element from the collection by a specified id.
 */
public class RemoveByIdCommand extends CollectionCommand {

    public RemoveByIdCommand(String name, CollectionManager collectionManager) {
        super(name, collectionManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        try {
            int id = Integer.parseInt(args[0]);
            HumanBeing objectToRemove = collectionManager
                    .getCollection()
                    .stream()
                    .filter(o -> o.getId().equals(id))
                    .findFirst().get();
            collectionManager.getCollection().remove(objectToRemove);
            System.out.printf("Object with ID %s was successfully removed\n", id);
        } catch (NoSuchElementException exception) {
            System.out.println("Object with given ID was not found");
        } catch (IllegalArgumentException exception) {
            System.out.println("Incorrect arguments. Should be: remove_by_id {id}");
        }
    }

    @Override
    public int numOfArgs() {
        return 1;
    }

    @Override
    public String getDescription() {
        return "removes element by given ID";
    }
}
