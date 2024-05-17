package ru.itmo.app.Database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itmo.app.Exceptions.UserException;
import ru.itmo.app.Interfaces.IAuthManager;
import static ru.itmo.app.Utilities.HashUtils.*;

import java.sql.*;

public class AuthManager implements IAuthManager {
    private static final Logger logger = LoggerFactory.getLogger(AuthManager.class);
    private Connection connection;
    private final int SALT_LENGTH = 128;
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public void auth(String user, String password) throws UserException, SQLException {
        if (user == null || password == null) throw new UserException("Login information was not provided.");
        if (!isExists(user) || !hash(password + getUserSalt(user)).equals(getUserPassword(user))) {
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
            String salt = generateString(SALT_LENGTH);
            String hashedPassword = hash(password + salt);
            String query = String.format("INSERT INTO users VALUES ('%s', '%s', '%s')", user, hashedPassword, salt);
            connection.createStatement().executeUpdate(query);
            logger.info("Successfully registered account " + "\"" + user + "\"" + ".");
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
            throw exception;
        }
    }
    public String getUserPassword(String user) throws SQLException {
        try {
            String query = String.format("SELECT users.password FROM users WHERE users.name = '%s'", user);
            ResultSet resultSet = connection.createStatement().executeQuery(query);
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
            String query = String.format("SELECT CAST(COUNT(1) AS INTEGER) FROM users WHERE users.name = '%s'", user);
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            if (resultSet.next()) {
                return resultSet.getBoolean(1);
            }
            return false;
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
            throw exception;
        }
    }
    public String getUserSalt(String user) throws SQLException {
        try {
            String query = String.format("SELECT salt FROM users WHERE name = '%s'", user);
            ResultSet resultSet = connection.createStatement().executeQuery(query);
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
