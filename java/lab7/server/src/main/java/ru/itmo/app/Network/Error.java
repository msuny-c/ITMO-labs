package ru.itmo.app.Network;

import ru.itmo.app.Exceptions.ServerException;

import java.io.Serial;
import java.io.Serializable;

public class Error implements Serializable {
    @Serial
    private static final long serialVersionUID = 1321685098267759690L;
    private final ServerException serverException;
    public Error(ServerException serverException) {
        this.serverException = serverException;
    }
}