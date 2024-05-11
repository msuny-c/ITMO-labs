package ru.itmo.app.Exceptions;

import java.io.Serial;

public class ServerException extends Exception {
    @Serial
    private static final long serialVersionUID = 2529685098267757690L;
    public ServerException(String message) {
        super(message);
    }
}
