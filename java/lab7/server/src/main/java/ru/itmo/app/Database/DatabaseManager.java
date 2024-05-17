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
    private static final Logger logger = LoggerFactory.getLogger(DatabaseManager.class);
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
    }
    public int add(T object) throws SQLException {
        return processor.add(object);
    }
    public void remove(Integer id) throws SQLException {
        processor.remove(id);
    }
    public void update(Integer id, T object) throws SQLException {
        processor.update(id, object);
    }
    public boolean isPermitted(Integer id, String username) throws SQLException {
        return processor.isPermitted(id, username);
    }
    public void setCollection(List<T> collection) throws SQLException {
        processor.setCollection(collection);
    }
    public List<T> getCollection() throws SQLException {
        return processor.getCollection();
    }
    public Connection getConnection() {
        return connection;
    }
}
