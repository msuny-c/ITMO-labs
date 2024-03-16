package ru.itmo.app.Managers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itmo.app.Exceptions.EmptyFileException;
import ru.itmo.app.Exceptions.IllegalPathException;
import ru.itmo.app.Models.HumanBeing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Stack;

public class CollectionManager {
    private static final Logger logger = LoggerFactory.getLogger(CollectionManager.class);
    private FileManager fileManager;
    private Stack<HumanBeing> collection = new Stack<>();
    private Date initTime = new Date();
    public final String TYPE = "HumanBeing";

    public CollectionManager(FileManager fileManager) {
        this.fileManager = fileManager;
        CollectionManager savedManager;
        try {
            savedManager = fileManager.load();
            if (savedManager.collection != null) collection = savedManager.collection;
            initTime = savedManager.initTime;
            logger.info("Collection was successfully loaded from " + fileManager.getAbsolutePath());
        } catch (IllegalPathException exception) {
            logger.warn("File path not specified. Collection wasn't loaded");
        } catch (FileNotFoundException exception) {
            logger.warn("The specified file in path was not found. Collection wasn't loaded");
        } catch (IOException exception) {
            logger.warn("Error while parsing collection. Collection wasn't loaded");
        } catch (EmptyFileException exception) {
            logger.warn("Collection file is empty. Collection wasn't loaded");
        }
    }
    public CollectionManager() {}
    public void save() {
        try {
            fileManager.save(this);
            logger.info("Collection was successfully saved to " + fileManager.getAbsolutePath());
        } catch (IllegalPathException exception) {
            logger.warn("File path not specified. Collection wasn't saved");
        } catch (FileNotFoundException exception) {
            logger.warn("The specified file in path was not found. Collection wasn't saved");
        } catch (IOException exception) {
            logger.warn("Error while saving collection. Collection wasn't saved");
        }
    }

    public Stack<HumanBeing> getCollection() {
        return collection;
    }
    public Date getInitTime() {
        return initTime;
    }
}