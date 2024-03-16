package ru.itmo.app;

import ru.itmo.app.Commands.*;
import ru.itmo.app.Managers.*;

import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException {

        FileManager fileManager = new FileManager("COLLECTION_PATH");
        CollectionManager collectionManager = new CollectionManager(fileManager);
        Runtime.getRuntime().addShutdownHook(new Thread(collectionManager::save));
        CommandManager commandManager = new CommandManager();

        commandManager.set(new AddCommand("add"));
        commandManager.set(new AvgOfSpeedCommand("average_of_impact_speed"));
        commandManager.set(new UpdateCommand("update"));
        commandManager.set(new ShowCommand("show"));
        commandManager.set(new ClearCommand("clear"));
        commandManager.set(new InfoCommand("info"));
        commandManager.set(new RemoveGreaterCommand("remove_greater"));
        commandManager.set(new ReorderCommand("reorder"));
        commandManager.set(new MaxBySpeed("max_by_impact_speed"));
        commandManager.set(new RemoveByIdCommand("remove_by_id"));
        commandManager.set(new FilterNameCommand("filter_starts_with_name"));
        commandManager.set(new AddIfMinCommand("add_if_min"));

        CommandHandler commandHandler = new CommandHandler(collectionManager, commandManager);
        ServerManager serverManager = new ServerManager(6661, commandHandler);
        serverManager.run();

    }
}