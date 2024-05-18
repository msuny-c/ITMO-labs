package ru.itmo.app.Interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface IAuthProcessor {
    void setConnection(Connection connection);
    String getUserPassword(String username) throws SQLException;
    boolean isExists(String username) throws SQLException;
    String getUserSalt(String username) throws SQLException;
    void insert(String username, String password, String salt) throws SQLException;
}
