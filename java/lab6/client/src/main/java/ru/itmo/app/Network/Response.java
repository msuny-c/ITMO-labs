package ru.itmo.app.Network;

import java.io.Serializable;

public record Response(String message, Status status, Error error) implements Serializable {
}
