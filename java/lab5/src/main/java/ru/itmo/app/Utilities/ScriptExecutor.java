package ru.itmo.app.Utilities;

import ru.itmo.app.Managers.CommandManager;
import ru.itmo.app.Managers.InputManager;
import ru.itmo.app.Managers.ScannerManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayDeque;

/**
 * Class to execute script from a file.
 */
public class ScriptExecutor {
    private final InputManager inputManager;
    private final FileInputStream fileInputStream;
    private static final ArrayDeque<Path> fileQueue = new ArrayDeque<>();
    private final Path path;

    /**
     * Checks if file contains in stacktrace.
     * @return true if file contains in stacktrace
     */
    public static boolean contains(Path path) {
        return fileQueue.contains(path.toAbsolutePath());
    }

    public ScriptExecutor(Path path, CommandManager commandManager) throws FileNotFoundException {
        this.fileInputStream = new FileInputStream((this.path = path).toFile());
        inputManager = new InputManager(commandManager);
    }

    /**
     * Executes script.
     * @throws FileNotFoundException if file not found.
     */
    public void execute() throws FileNotFoundException {
        fileQueue.push(path.toAbsolutePath());
        inputManager.run(new ScannerManager(fileInputStream));
        fileQueue.pop();
    }
}
