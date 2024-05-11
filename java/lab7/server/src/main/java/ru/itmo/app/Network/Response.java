package ru.itmo.app.Network;

import java.io.Serializable;
import ru.itmo.app.Network.Error;
public record Response(String message, Status status, Error error) implements Serializable {
}
