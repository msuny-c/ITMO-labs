package ru.itmo.app.Interfaces;

import ru.itmo.app.Managers.ScannerManager;

/**
 * Interface describing a command.
 */
public interface Command extends Describable {
    void execute(String[] args, ScannerManager scannerManager);
    int numOfArgs();
}
