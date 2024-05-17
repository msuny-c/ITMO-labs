package ru.itmo.app.Interfaces;

import ru.itmo.app.Network.CommandRequest;
import ru.itmo.app.Network.Response;

public interface ICommandHandler {
    Response handle(CommandRequest request);
}
