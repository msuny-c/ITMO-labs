package ru.itmo.app.Exceptions;

public class ServerException extends Exception {
    private static final long serialVersionUID = 2529685098267757690L;
    public ServerException(String message) {
        super(message);
    }
}
