package ru.itmo.app.Managers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itmo.app.Database.DatabaseManager;
import ru.itmo.app.Exceptions.NoPermissionException;
import ru.itmo.app.Models.HumanBeing;
import ru.itmo.app.Network.Session;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CollectionManager {
    private static final Logger logger = LoggerFactory.getLogger(CollectionManager.class);
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private final DatabaseManager<HumanBeing> databaseManager;
    private List<HumanBeing> collection;

    public CollectionManager(DatabaseManager<HumanBeing> databaseManager) {
        this.databaseManager = databaseManager;
        try {
            collection = databaseManager.getCollection();
        } catch (SQLException exception) {
            logger.error("Error occurred while loading collection from the database.", exception);
        }
    }
    public void add(HumanBeing object) {
        try {
            writeLock.lock();
            object.setUser(Session.getCurrentUser());
            int id = databaseManager.add(object);
            object.setId(id);
            collection = databaseManager.getCollection();
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        } finally {
            writeLock.unlock();
        }
    }

    public void update(Integer id, HumanBeing object) throws NoPermissionException {
        try {
            writeLock.lock();
            requirePresence(id);
            if (databaseManager.isPermitted(id, Session.getCurrentUser())) {
                databaseManager.update(id, object);
                var old = collection.stream().filter(o -> Objects.equals(o.id(), id)).findFirst().get();
                old.update(object);
            }
            throw new NoPermissionException();
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        } finally {
            writeLock.unlock();
        }
    }
    public void clear() throws NoPermissionException {
        try {
            writeLock.lock();
            var belongs = collection.stream().filter(o -> o.getUser().equals(Session.getCurrentUser())).toList();
            for (HumanBeing belong: belongs) {
                databaseManager.remove(belong.id());
            }
            collection.removeIf(o -> o.getUser().equals(Session.getCurrentUser()));
            if (belongs.isEmpty()) {
                throw new NoPermissionException();
            };
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        } finally {
            writeLock.unlock();
        }
    }

    public void remove(Integer id) throws NoPermissionException {
        try {
            writeLock.lock();
            requirePresence(id);
            if (databaseManager.isPermitted(id, Session.getCurrentUser())) {
                databaseManager.remove(id);
                collection.removeIf(o -> Objects.equals(o.id(), id));
            }
            throw new NoPermissionException();
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        } finally {
            writeLock.unlock();
        }
    }
    public void reorder() {
        try {
            writeLock.lock();
            List<HumanBeing> reordered = new Stack<>();
            for (int i = collection.size() - 1; i >= 0; i--) reordered.add(collection.get(i));
            databaseManager.setCollection(reordered);
            collection = reordered;
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        } finally {
            writeLock.unlock();
        }
    }
    public void removeGreater(HumanBeing object) {
        try {
            writeLock.lock();
            var belongs = collection.stream().filter(o -> o.getUser().equals(Session.getCurrentUser())).toList();
            for (HumanBeing belong: belongs) {
                if (belong.compareTo(object) > 0) {
                    databaseManager.remove(belong.id());
                    collection.remove(belong);
                }
            }
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        } finally {
            writeLock.unlock();
        }

    }
    public boolean addIfMin(HumanBeing object) {
        try {
            writeLock.lock();
            if (object.compareTo(Collections.min(collection)) < 0) {
                add(object);
                return true;
            }
            return false;
        } finally {
            writeLock.unlock();
        }
    }


    public Collection<HumanBeing> getCollection() {
        try {
            readLock.lock();
            return collection;
        } finally {
            readLock.unlock();
        }
    }
    private void requirePresence(int id) {
        try {
            readLock.lock();
            if (!collection.stream().map(HumanBeing::id).toList().contains(id)) {
                throw new NoSuchElementException();
            };
        } finally {
            readLock.unlock();
        }
    }
}