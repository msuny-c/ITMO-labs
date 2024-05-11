package ru.itmo.app.Managers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itmo.app.Exceptions.UserException;

import java.sql.*;

public class AuthManager {
    private static final Logger logger = LoggerFactory.getLogger(AuthManager.class);
    private Connection connection;
    private final HashManager hashManager;
    public AuthManager(HashManager hashManager) {
        this.hashManager = hashManager;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public void authUser(String user, String password) throws UserException, SQLException {
        if (user == null || password == null) throw new UserException("Login information was not provided.");
        if (!isExists(user) || !hashManager.getHash(password + getSalt(user)).equals(getUserPassword(user))) {
            logger.warn("Failed login attempt to the account " + "\"" + user + "\"" + ".");
            throw new UserException("The provided password is invalid.");
        }
        logger.info("Success login to the account " + "\"" + user + "\"" + ".");
    }
    public void register(String user, String password) throws UserException, SQLException {
        if (user == null || password == null) throw new UserException("Login information was not provided.");
        if (isExists(user)) {
            logger.warn("Failed to register user " + "\"" + user + "\"" + ".");
            throw new UserException("User with the same name already exists.");
        }
        try {
            String salt = hashManager.generateSalt();
            String hashedPassword = hashManager.getHash(password + salt);
            String query = "INSERT INTO users VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user);
            statement.setString(2, hashedPassword);
            statement.setString(3, salt);
            statement.executeUpdate();
            logger.info("Successfully registered account " + "\"" + user + "\"" + ".");
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
            throw exception;
        }
    }
    public String getUserPassword(String user) throws SQLException {
        try {
            String query = "SELECT users.password FROM users WHERE users.name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
            return null;
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
            throw exception;
        }
    }
    public boolean isExists(String user) throws SQLException {
        try {
            String query = "SELECT CAST(COUNT(1) AS INTEGER) FROM users WHERE users.name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean(1);
            }
            return false;
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
            throw exception;
        }
    }
    public String getSalt(String user) throws SQLException {
        try {
            String query = "SELECT salt FROM users WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("salt");
            }
            return null;
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
            throw exception;
        }
    }
}
