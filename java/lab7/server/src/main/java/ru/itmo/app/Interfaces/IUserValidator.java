package ru.itmo.app.Interfaces;

import ru.itmo.app.Exceptions.AuthException;

public interface IUserValidator {
    void validate(String username, String password) throws AuthException;
    void requireNonNull(String username, String password) throws AuthException;
}
