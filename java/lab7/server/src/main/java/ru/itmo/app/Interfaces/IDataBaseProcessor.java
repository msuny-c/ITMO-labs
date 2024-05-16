package ru.itmo.app.Interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IDataBaseProcessor<T> {
    void setConnection(Connection connection);
    int add(T object) throws SQLException;
    void update(Integer id, T object) throws SQLException;
    void remove(Integer id) throws SQLException;
    boolean isPermitted(Integer id, String username) throws SQLException;
    void setCollection(List<T> collection) throws SQLException;
    List<T> getCollection() throws SQLException;
}
