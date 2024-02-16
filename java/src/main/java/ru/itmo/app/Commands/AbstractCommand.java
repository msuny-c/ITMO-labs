package ru.itmo.app.Commands;

import ru.itmo.app.Interfaces.Command;

/**
 * Abstract class describing a command.
 */
public abstract class AbstractCommand implements Command {
    private final String name;
    public AbstractCommand(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
