package ru.itmo.app.Managers;
import ru.itmo.app.Exceptions.CommandNotFoundException;
import ru.itmo.app.Exceptions.EOFException;

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
        if (scannerManager.isSystemStream) {
            System.out.println("Welcome to the CLI! Type \"help\" to see available commands.");
        }
        while (true) {
            try {
                String input = scannerManager.nextLine().trim();
                String[] args = input.split(" +");
                commandManager.execute(args[0].trim(), Arrays.copyOfRange(args, 1, args.length), scannerManager);
            } catch (CommandNotFoundException exception) {
                System.out.println("Command not found. Please try again:");
            } catch (IllegalArgumentException exception) {
                System.out.println("Wrong argument. Please try again:");
            } catch (EOFException exception) {
                System.out.print("End of file.");
            }
        }
    }
}