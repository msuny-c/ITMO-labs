package ru.itmo.app.Database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itmo.app.Exceptions.AuthException;
import ru.itmo.app.Interfaces.IAuthManager;
import ru.itmo.app.Interfaces.IAuthProcessor;
import ru.itmo.app.Interfaces.IHashManager;
import ru.itmo.app.Interfaces.IUserValidator;

import java.sql.*;

public class AuthManager implements IAuthManager {
    private static final Logger logger = LoggerFactory.getLogger(AuthManager.class);
    private final IUserValidator validator;
    private final IAuthProcessor processor;
    private final IHashManager hashManager;
    private final int SALT_LENGTH = 128;
    public void setConnection(Connection connection) {
        processor.setConnection(connection);
    }
    public AuthManager(IUserValidator validator, IAuthProcessor processor, IHashManager hashManager) {
        this.validator = validator;
        this.processor = processor;
        this.hashManager = hashManager;
    }
    @Override
    public void auth(String username, String password) throws AuthException, SQLException {
        validator.requireNonNull(username, password);
        username = username.toLowerCase();
        if (!processor.isExists(username) || !hashManager.getHash(password + processor.getUserSalt(username)).equals(processor.getUserPassword(username))) {
            logger.warn("Failed login attempt to the account " + "\"" + username + "\"" + ".");
            throw new AuthException("The provided password is invalid.");
        }
        logger.info("Successful login to the account " + "\"" + username + "\"" + ".");
    }
    @Override
    public void register(String username, String password) throws AuthException, SQLException {
        try {
            if (processor.isExists(username)) {
                throw new AuthException("User with the same name already exists.");
            }
            validator.validate(username, password);
        } catch (AuthException exception) {
            logger.warn("Failed to register an account " + "\"" + username + "\"" + ".");
            throw exception;
        }
        String salt = hashManager.generateString(SALT_LENGTH);
        String hashedPassword = hashManager.getHash(password + salt);
        processor.insert(username, hashedPassword, salt);
        logger.info("Successfully registered account " + "\"" + username + "\"" + ".");
    }
}
