package ru.itmo.app.Commands;

import ru.itmo.app.Interfaces.Describable;
import ru.itmo.app.Managers.CommandManager;
import ru.itmo.app.Managers.ScannerManager;

/**
 * Class for command 'help'
 * displays available commands.
 */
public class HelpCommand extends AbstractCommand {
    CommandManager commandManager;
    public HelpCommand(String name, CommandManager commandManager) {
        super(name);
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        System.out.println("Information about available commands:");
        for (Describable object : commandManager.getDescriptions())
            System.out.println("\t" + object.getName() + ": " + object.getDescription());
    }

    @Override
    public int numOfArgs() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "displays available commands";
    }
}
