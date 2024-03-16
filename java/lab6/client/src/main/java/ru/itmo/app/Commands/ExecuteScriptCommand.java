package ru.itmo.app.Commands;

import ru.itmo.app.Managers.CommandManager;
import ru.itmo.app.Managers.ScannerManager;
import ru.itmo.app.Utilities.ScriptExecutor;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class for command 'execute_script'
 * executes script from specified file.
 */
public class ExecuteScriptCommand extends AbstractCommand {
    private final CommandManager commandManager;
    public ExecuteScriptCommand(String name, CommandManager commandManager) {
        super(name);
        this.commandManager = commandManager;
    }
    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        try {
            Path path = Paths.get(args[0]);
            if (!ScriptExecutor.contains(path)) {
                ScriptExecutor scriptExecutor = new ScriptExecutor(path, commandManager);
                scriptExecutor.execute();
            }
        } catch (FileNotFoundException exception) {
            System.out.println("Script file was not found. Please try again:");
        }
    }
    @Override
    public int numOfArgs() {
        return 1;
    }

    @Override
    public String getDescription() {
        return "executes the script from the specified file";
    }
}
