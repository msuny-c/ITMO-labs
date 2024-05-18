package ru.itmo.app.Managers;

import ru.itmo.app.Exceptions.*;
import ru.itmo.app.Interfaces.ICommandManager;
import ru.itmo.app.Models.HumanBeing;
import ru.itmo.app.Interfaces.ICommand;
import java.util.HashMap;

public class CommandManager implements ICommandManager<HumanBeing> {
    private final HashMap<String, ICommand> commands = new HashMap<>();
    public void set(ICommand command) {
        commands.put(command.getName(), command);
    }

    public String execute(CollectionManager collectionManager, String name, HumanBeing object, String[] args) throws CommandNotFoundException {
        if (!commands.containsKey(name)) throw new CommandNotFoundException();
        return commands.get(name).execute(collectionManager, object, args);
}
}
