package ru.itmo.app.Interfaces;

import ru.itmo.app.Exceptions.UserException;
import java.sql.SQLException;

public interface IAuthManager {
    void register(String username, String password) throws UserException, SQLException;
    void authUser(String username, String password) throws UserException, SQLException;
}
