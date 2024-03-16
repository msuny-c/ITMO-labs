package ru.itmo.app.Commands;

import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;

public class FilterNameCommand extends AbstractCommand {
    public FilterNameCommand(String name) {
        super(name);
    }

    @Override
    public String execute(CollectionManager collectionManager, HumanBeing object, String[] args) {
        StringBuilder message = new StringBuilder();
        for (HumanBeing human : collectionManager.getCollection()) {
            if (human.getName().startsWith(String.join(" ", args))) message.append(human).append("\n");
        }
        if (message.isEmpty()) {
            return "There's no objects which name starts with specified substring.";
        } else {
            message.deleteCharAt(message.length() - 1);
            return message.toString();
        }
    }

    @Override
    public int numOfArgs() {
        return -1;
    }
}
