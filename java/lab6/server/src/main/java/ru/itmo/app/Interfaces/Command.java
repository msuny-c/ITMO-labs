package ru.itmo.app.Interfaces;

import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;

public interface Command {
    String execute (CollectionManager collectionManager, HumanBeing object, String[] args);
    int numOfArgs();
    String getName();
}
