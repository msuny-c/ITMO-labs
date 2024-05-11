package ru.itmo.app.Commands;

import ru.itmo.app.Interfaces.Command;

public abstract class AbstractCommand implements Command {
    private final String name;
    public AbstractCommand(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
