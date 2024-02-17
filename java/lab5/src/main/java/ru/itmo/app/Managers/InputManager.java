package ru.itmo.app.Managers;
import ru.itmo.app.Exceptions.CommandNotFoundException;

import java.util.Arrays;

/**
 * Class to read commands from user.
 */
public class InputManager {
    private final CommandManager commandManager;
    public InputManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    /**
     * Starts to read commands.
     * @param scannerManager scannerManager.
     */
    public void run(ScannerManager scannerManager) {
        if (scannerManager.isSystemIn) {
            System.out.println("Welcome to the CLI! Type \"help\" to see available commands.");
        }
        while (scannerManager.scanner.hasNext()) {
            try {
                String inputLine = scannerManager.scanner.nextLine().trim();
                if (inputLine.isEmpty()) throw new CommandNotFoundException();
                String[] args = inputLine.split(" +");
                commandManager.execute(args[0], Arrays.copyOfRange(args, 1, args.length), scannerManager);
            } catch (CommandNotFoundException exception) {
                System.out.println("Command not found. Please try again: ");
            } catch (IllegalArgumentException exception) {
                System.out.println("Wrong argument. Please try again: ");
            }
        }
    }
}