package ru.itmo.app.Managers;

import ru.itmo.app.Exceptions.*;
import ru.itmo.app.Network.CommandRequest;
import ru.itmo.app.Network.Response;
import ru.itmo.app.Network.Status;
import ru.itmo.app.Network.Error;

public class CommandHandler {
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;
    public CommandHandler(CollectionManager collectionManager, CommandManager commandManager) {
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }
    public Response handle(CommandRequest request) {
        try {
            String result = commandManager.execute(collectionManager, request.command(), request.object(), request.args());
            return new Response(result, Status.OK, new Error(null));
        } catch (CommandNotFoundException exception) {
            return new Response(null, Status.FAIL, new Error(new ServerException("Command \"" + request.command() + "\" not found.")));
        } catch (NullPointerException exception) {
            return new Response(null, null, new Error(new ServerException("Objects or arguments wasn't provided to execute \"" + request.command() + "\" command.")));
        } catch (IllegalArgumentException exception) {
            return new Response(null, null, new Error(new ServerException("Provided illegal arguments.")));
        }
    }
}
