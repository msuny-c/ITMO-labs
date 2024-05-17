package ru.itmo.app.Commands;

import ru.itmo.app.Interfaces.ICommand;

public abstract class AbstractCommand implements ICommand {
    private final String name;
    public AbstractCommand(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
