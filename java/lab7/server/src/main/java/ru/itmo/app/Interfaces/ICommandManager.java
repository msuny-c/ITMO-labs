package ru.itmo.app.Interfaces;

import ru.itmo.app.Exceptions.CommandNotFoundException;
import ru.itmo.app.Exceptions.UserException;
import ru.itmo.app.Managers.CollectionManager;

public interface ICommandManager<T> {
    void set(ICommand command);
    String execute(CollectionManager collectionManager, String name, T object, String[] args) throws CommandNotFoundException, UserException;
}
