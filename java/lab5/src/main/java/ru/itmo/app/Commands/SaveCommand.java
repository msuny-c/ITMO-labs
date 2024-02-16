package ru.itmo.app.Commands;

import ru.itmo.app.Exceptions.IllegalPathException;
import ru.itmo.app.Managers.*;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Class for command 'save'
 * saves the collection to a specified file by environment variable.
 */
public class SaveCommand extends CollectionCommand {
    private final FileManager fileManager;
    public SaveCommand(String name, CollectionManager collectionManager, FileManager fileManager) {
        super(name, collectionManager);
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        try {
            fileManager.save(collectionManager);
            System.out.println("Collection was successfully saved to " + fileManager.getAbsolutePath());
        } catch (IllegalPathException exception) {
            System.out.println("File path not specified. Collection wasn't saved");
        } catch (FileNotFoundException exception) {
            System.out.println("The specified file in path was not found. Collection wasn't saved");
        } catch (IOException exception) {
            System.out.println("Error while saving collection. Collection wasn't saved");
        }
    }

    @Override
    public int numOfArgs() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "saves collection to specified file in environment variable";
    }
}
