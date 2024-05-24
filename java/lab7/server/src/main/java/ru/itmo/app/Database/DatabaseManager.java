package ru.itmo.app.Database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itmo.app.Interfaces.IDataBaseProcessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class DatabaseManager<T> {
    private final IDataBaseProcessor<T> processor;
    private Connection connection;
    public DatabaseManager(IDataBaseProcessor<T> processor) {
        this.processor = processor;
    }
    public void connect(String url, String username, String password) throws SQLException {
        Properties auth = new Properties();
        auth.put("user", username);
        auth.put("password", password);
        connection = DriverManager.getConnection(url, auth);
        processor.setConnection(connection);
        connection.setAutoCommit(false);
    }
    public synchronized int add(T object) throws SQLException {
        try {
            int id = processor.add(object);
            connection.commit();
            return id;
        } catch (SQLException exception) {
            connection.rollback();
            throw exception;
        }
    }
    public synchronized void remove(Integer id) throws SQLException {
        try {
            processor.remove(id);
            connection.commit();
        } catch (SQLException exception) {
            connection.rollback();
            throw exception;
        }
    }
    public synchronized void update(Integer id, T object) throws SQLException {
        try {
            processor.update(id, object);
            connection.commit();
        } catch (SQLException exception) {
            connection.rollback();
            throw exception;
        }
    }
    public synchronized boolean isPermitted(Integer id, String username) throws SQLException {
        return processor.isPermitted(id, username);
    }
    public synchronized void setCollection(List<T> collection) throws SQLException {
        try {
            processor.setCollection(collection);
            connection.commit();
        } catch (SQLException exception) {
            connection.rollback();
            throw exception;
        }
    }
    public synchronized List<T> getCollection() throws SQLException {
        return processor.getCollection();
    }
    public Connection getConnection() {
        return connection;
    }
}
