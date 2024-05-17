package ru.itmo.app.Interfaces;

import ru.itmo.app.Exceptions.UserException;
import java.sql.SQLException;

public interface IAuthManager {
    void register(String username, String password) throws UserException, SQLException;
    void auth(String username, String password) throws UserException, SQLException;
}
