package ru.itmo.app.Commands;

import ru.itmo.app.Managers.ScannerManager;

/**
 * Class for command 'exit'
 * terminates the application.
 */
public class ExitCommand extends AbstractCommand {

    public ExitCommand(String name) {
        super(name);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        System.out.println("Closing the application...");
        scannerManager.scanner.close();
        System.exit(0);
    }

    @Override
    public int numOfArgs() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "terminates the application (without saving)";
    }
}
