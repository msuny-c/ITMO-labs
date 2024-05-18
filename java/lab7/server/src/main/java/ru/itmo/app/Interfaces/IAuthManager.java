package ru.itmo.app.Interfaces;

import ru.itmo.app.Exceptions.AuthException;
import java.sql.SQLException;

public interface IAuthManager {
    void register(String username, String password) throws AuthException, SQLException;
    void auth(String username, String password) throws AuthException, SQLException;
}
