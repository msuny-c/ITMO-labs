package ru.itmo.app.Commands;

import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;

public class InfoCommand extends AbstractCommand {
    public InfoCommand(String name) {
        super(name);
    }

    @Override
    public String execute(CollectionManager collectionManager, HumanBeing object, String[] args) {
        return "";
    }

    @Override
    public int numOfArgs() {
        return 0;
    }
}
