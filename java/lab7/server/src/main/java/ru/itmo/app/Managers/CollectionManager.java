package ru.itmo.app.Managers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itmo.app.Database.DatabaseManager;
import ru.itmo.app.Exceptions.NotPermissionException;
import ru.itmo.app.Models.HumanBeing;
import ru.itmo.app.Network.Session;

import java.sql.SQLException;
import java.util.*;

public class CollectionManager {
    private static final Logger logger = LoggerFactory.getLogger(CollectionManager.class);
    private final DatabaseManager<HumanBeing> databaseManager;
    private List<HumanBeing> collection = new Stack<>();

    public CollectionManager(DatabaseManager<HumanBeing> databaseManager) {
        this.databaseManager = databaseManager;
        try {
            collection = databaseManager.getCollection();
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
            logger.error("Error occurred while loading collection from the database.");
        }
    }
    public void add(HumanBeing object) {
        try {
            object.setUser(Session.getCurrentUser());
            int id = databaseManager.add(object);
            object.setId(id);
            collection.add(object);
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }
    }

    public void update(Integer id, HumanBeing object) throws NotPermissionException {
        try {
            if (databaseManager.isPermitted(id, Session.getCurrentUser())) {
                databaseManager.update(id, object);
                var objectToUpdate = collection.stream().filter(o -> o.id() == id).findFirst().get();
                objectToUpdate.update(object);
            }
            throw new NotPermissionException();
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }
    }
    public void clear() throws NotPermissionException {
        try {
            var objectsOfUser = collection.stream().filter(o -> o.getUser().equals(Session.getCurrentUser())).toList();
            for (HumanBeing object: objectsOfUser) {
                databaseManager.remove(object.id());
            }
            collection.removeIf(o -> o.getUser().equals(Session.getCurrentUser()));
            if (objectsOfUser.isEmpty()) {
                throw new NotPermissionException();
            };
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }
    }

    public void remove(Integer id) throws NotPermissionException {
        try {
            if (databaseManager.isPermitted(id, Session.getCurrentUser())) {
                databaseManager.remove(id);
                collection.removeIf(o -> Objects.equals(o.id(), id));
            }
            throw new NotPermissionException();
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }
    }
    public void reorder() {
        try {
            List<HumanBeing> reordered = new Stack<>();
            for (int i = collection.size() - 1; i >= 0; i--) reordered.add(collection.get(i));
            databaseManager.setCollection(reordered);
            collection = reordered;
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }
    }
    public void removeGreater(HumanBeing object) {
        try {
            var objectsOfUser = collection.stream().filter(o -> o.getUser().equals(Session.getCurrentUser())).toList();
            for (HumanBeing in_object: objectsOfUser) {
                if (in_object.compareTo(object) > 0) {
                    databaseManager.remove(in_object.id());
                }
            }
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }

    }
    public boolean addIfMin(HumanBeing object) {
        if (object.compareTo(Collections.min(collection)) < 0) {
            add(object);
            return true;
        }
        return false;
    }


    public Collection<HumanBeing> getCollection() {
        return collection;
    }
}