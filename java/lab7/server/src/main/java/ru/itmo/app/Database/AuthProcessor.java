package ru.itmo.app.Database;

import ru.itmo.app.Interfaces.IAuthProcessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthProcessor implements IAuthProcessor {
    private Connection connection;
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    @Override
    public String getUserPassword(String username) throws SQLException {
        String query = String.format("SELECT users.password FROM users WHERE users.name = '%s'", username);
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean isExists(String username) throws SQLException {
        String query = String.format("SELECT CAST(COUNT(1) AS INTEGER) FROM users WHERE users.name = '%s'", username);
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next()) {
            return resultSet.getBoolean(1);
        }
        return false;
    }

    @Override
    public String getUserSalt(String username) throws SQLException {
        String query = "SELECT salt FROM users WHERE name = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getString("salt");
    }

    @Override
    public void insert(String username, String password, String salt) throws SQLException {
        String query = "INSERT INTO users (name, password, salt) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        statement.setString(3, salt);
        statement.executeUpdate();
    }
}
