package ru.itmo.app.Managers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import ru.itmo.app.Interfaces.Command;
import ru.itmo.app.Interfaces.Describable;
import ru.itmo.app.Exceptions.CommandNotFoundException;

/**
 * Class to control commands.
 */
public class CommandManager {
    private final HashMap<String, Command> commands = new HashMap<>();

    /**
     * Sets a new command.
     * @param object Command object.
     */
    public void set(Command object) {
        commands.put(object.getName(), object);
    }

    /**
     * Executes a command.
     * @param name name of a command.
     * @param args arguments from a user.
     * @param scannerManager scannerManager.
     * @throws CommandNotFoundException if command not found.
     */
    public void execute(String name, String[] args, ScannerManager scannerManager) throws CommandNotFoundException {
        if (!commands.containsKey(name)) throw new CommandNotFoundException();
        if (commands.get(name).numOfArgs() != args.length && commands.get(name).numOfArgs() >= 0) throw new IllegalArgumentException();
        commands.get(name).execute(args, scannerManager);
    }

    /**
     * @return List of descriptions of commands.
     */
    public Collection<Describable> getDescriptions() {
        return new ArrayList<>(commands.values());
    }
}
