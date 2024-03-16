package ru.itmo.app.Network;

import ru.itmo.app.Commands.ServerCommand;
import ru.itmo.app.Exceptions.ServerException;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Error implements Serializable {
    @Serial
    private static final long serialVersionUID = 1321685098267759690L;
    private final ServerException serverException;
    public Error(ServerException serverException) {
        this.serverException = serverException;
    }
    public void printError() {
        System.out.println("SERVER ERROR: " + serverException.getMessage());
    }
    public boolean hasError() {
        return serverException != null;
    }
}
