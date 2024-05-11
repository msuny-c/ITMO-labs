package ru.itmo.app.Commands;

import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;

import java.util.Comparator;

public class ShowCommand extends AbstractCommand {
    public ShowCommand(String name) {
        super(name);
    }

    @Override
    public String execute(CollectionManager collectionManager, HumanBeing object, String[] args) {
        StringBuilder message = new StringBuilder();
        if (collectionManager.getCollection().isEmpty()) return "Collection is empty.";
        for (HumanBeing human : collectionManager.getCollection()) {
            message.append(human.toString()).append("\n");
        }
        message.deleteCharAt(message.length() - 1);
        return message.toString();
    }

    @Override
    public int numOfArgs() {
        return 0;
    }
}
