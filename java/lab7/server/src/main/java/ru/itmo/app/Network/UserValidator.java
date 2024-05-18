package ru.itmo.app.Network;

import ru.itmo.app.Exceptions.AuthException;
import ru.itmo.app.Interfaces.IUserValidator;

public class UserValidator implements IUserValidator {
    private final int MAX_USERNAME_LENGTH = 64;
    private final int MIN_USERNAME_LENGTH = 5;
    private final int MAX_PASSWORD_LENGTH = 64;
    private final int MIN_PASSWORD_LENGTH = 8;
    @Override
    public void validate(String username, String password) throws AuthException {
        requireNonNull(username, password);
        validateUsername(username);
        validatePassword(password);
    }
    @Override
    public void requireNonNull(String username, String password) throws AuthException {
        if (username == null || password == null) {
            throw new AuthException("Login information was not provided.");
        }
    }
    private void validateUsername(String username) throws AuthException {
        if (username.contains(" ")) throw new AuthException("Username cannot contain spaces.");
        validateUsernameLength(username);
    }
    private void validateUsernameLength(String username) throws AuthException {
        if (username.length() > MAX_USERNAME_LENGTH) {
            throw new AuthException("Username length cannot exceed 64 characters.");
        }
        if (username.length() < MIN_USERNAME_LENGTH) {
            throw new AuthException("Username length cannot be less than 5 characters.");
        }
    }
    private void validatePassword(String password) throws AuthException {
        validatePasswordLength(password);
        validateDifficulty(password);
    }
    private void validatePasswordLength(String password) throws AuthException {
        if (password.length() > MAX_PASSWORD_LENGTH) {
            throw new AuthException("Password length cannot exceed 64 characters.");
        }
        if (password.length() < MIN_USERNAME_LENGTH) {
            throw new AuthException("Password length cannot be less than 8 characters.");
        }
    }
    private void validateDifficulty(String password) throws AuthException {
        if (password.toLowerCase().equals(password)) {
            throw new AuthException("Password must contain at least one character in uppercase.");
        }
        if (password.toUpperCase().equals(password)) {
            throw new AuthException("Password must contain at least one character in lowercase.");
        }
        if (!password.matches(".*\\d.*")) {
            throw new AuthException("Password must contain at least one digit.");
        }
    }
}
