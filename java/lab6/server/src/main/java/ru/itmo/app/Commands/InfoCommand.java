package ru.itmo.app.Commands;

import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;

public class InfoCommand extends AbstractCommand {
    public InfoCommand(String name) {
        super(name);
    }

    @Override
    public String execute(CollectionManager collectionManager, HumanBeing object, String[] args) {
        return String.format("Information about collection:\n" +
                        "\tCollection type: %s\n" + "\tInitialization date: %s\n" + "\tCollection size: %s",
                collectionManager.TYPE, collectionManager.getInitTime(), collectionManager.getCollection().size());
    }

    @Override
    public int numOfArgs() {
        return 0;
    }

    @Override
    public String getName() {
        return "info";
    }
}
