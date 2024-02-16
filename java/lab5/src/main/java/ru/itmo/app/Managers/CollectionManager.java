package ru.itmo.app.Managers;

import ru.itmo.app.Exceptions.IllegalPathException;
import ru.itmo.app.Models.HumanBeing;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Stack;

/**
 * Class to control collection.
 */
public class CollectionManager {
    private Stack<HumanBeing> collection = new Stack<>();

    /**
     * Constructor to load saved collection from a file.
     * @param fileManager object of FileManager class.
     */
    public CollectionManager(FileManager fileManager) {
        CollectionManager savedManager;
        try {
            savedManager = fileManager.load();
            if (savedManager.collection != null) collection = savedManager.collection;
            initTime = savedManager.initTime;
        } catch (IllegalPathException exception) {
            System.out.println("File path not specified. Collection wasn't loaded");
        } catch (FileNotFoundException exception) {
            System.out.println("The specified file in path was not found. Collection wasn't loaded");
        } catch (IOException exception) {
            System.out.println("Error while parsing collection. Collection wasn't loaded");
        }
    }
    public CollectionManager() {}

    /**
     * Type of the objects in the collection.
     */
    public final String TYPE = "HumanBeing";
    /**
     * Collection's creation date.
     */
    private Date initTime = new Date();
    public Stack<HumanBeing> getCollection() {
        return collection;
    }
    public Date getInitTime() {
        return initTime;
    }
}