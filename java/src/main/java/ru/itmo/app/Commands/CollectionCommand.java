package ru.itmo.app.Commands;
import ru.itmo.app.Managers.*;
import ru.itmo.app.Models.*;

/**
 * Abstract class describing a command that uses collection
 */
public abstract class CollectionCommand extends AbstractCommand {
   CollectionManager collectionManager;
    public CollectionCommand(String name, CollectionManager collectionManager) {
        super(name);
        this.collectionManager = collectionManager;
    }
}
