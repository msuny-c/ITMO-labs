package ru.itmo.app.Managers;

import ru.itmo.app.Exceptions.CommandNotFoundException;
import ru.itmo.app.Models.HumanBeing;
import ru.itmo.app.Interfaces.Command;
import java.util.HashMap;

public class CommandManager {
    private final HashMap<String, Command> commands = new HashMap<>();
    public void set(Command command) {
        commands.put(command.getName(), command);
    }

    public String execute(CollectionManager collectionManager, String name, HumanBeing object, String[] args) throws CommandNotFoundException {
        if (!commands.containsKey(name)) throw new CommandNotFoundException();
        return commands.get(name).execute(collectionManager, object, args);
}
}
