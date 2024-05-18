package ru.itmo.app.Database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itmo.app.Exceptions.AuthException;
import ru.itmo.app.Interfaces.IAuthManager;
import ru.itmo.app.Interfaces.IAuthProcessor;
import ru.itmo.app.Interfaces.IUserValidator;

import static ru.itmo.app.Utilities.HashUtils.*;

import java.sql.*;

public class AuthManager implements IAuthManager {
    private static final Logger logger = LoggerFactory.getLogger(AuthManager.class);
    private final IUserValidator validator;
    private final IAuthProcessor processor;
    private final int SALT_LENGTH = 128;
    public void setConnection(Connection connection) {
        processor.setConnection(connection);
    }
    public AuthManager(IUserValidator validator, IAuthProcessor processor) {
        this.validator = validator;
        this.processor = processor;
    }
    @Override
    public void auth(String username, String password) throws AuthException, SQLException {
        validator.requireNonNull(username, password);
        if (!processor.isExists(username) || !hash(password + processor.getUserSalt(username)).equals(processor.getUserPassword(username))) {
            logger.warn("Failed login attempt to the account " + "\"" + username + "\"" + ".");
            throw new AuthException("The provided password is invalid.");
        }
        logger.info("Successful login to the account " + "\"" + username + "\"" + ".");
    }
    @Override
    public void register(String username, String password) throws AuthException, SQLException {
        if (processor.isExists(username)) {
            logger.warn("Failed to register an account " + "\"" + username + "\"" + ".");
            throw new AuthException("User with the same name already exists.");
        }
        validator.validate(username, password);
        String salt = generateString(SALT_LENGTH);
        String hashedPassword = hash(password + salt);
        processor.insert(username, hashedPassword, salt);
        logger.info("Successfully registered account " + "\"" + username + "\"" + ".");
    }
}
