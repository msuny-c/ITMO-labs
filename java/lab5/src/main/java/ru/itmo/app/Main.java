package ru.itmo.app;
import ru.itmo.app.Commands.*;
import ru.itmo.app.Managers.*;
import ru.itmo.app.Managers.FileManager;
import ru.itmo.app.Managers.ScannerManager;

public class Main {
    public static void main(String[] args) {

        FileManager fileManager = new FileManager("COLLECTION_PATH");

        CollectionManager collectionManager = new CollectionManager(fileManager);
        CommandManager commandManager = new CommandManager();

        commandManager.set(new ClearCommand("clear", collectionManager));
        commandManager.set(new ShowCommand("show", collectionManager));
        commandManager.set(new AddCommand("add", collectionManager));
        commandManager.set(new InfoCommand("info", collectionManager));
        commandManager.set(new ReorderCommand("reorder", collectionManager));
        commandManager.set(new UpdateCommand("update", collectionManager));
        commandManager.set(new HelpCommand("help", commandManager));
        commandManager.set(new RemoveByIdCommand("remove_by_id", collectionManager));
        commandManager.set(new MaxBySpeedCommand("max_by_impact_speed", collectionManager));
        commandManager.set(new AddIfMinCommand("add_if_min", collectionManager));
        commandManager.set(new RemoveGreaterCommand("remove_greater", collectionManager));
        commandManager.set(new ExitCommand("exit"));
        commandManager.set(new SaveCommand("save", collectionManager, fileManager));
        commandManager.set(new FilterNameCommand("filter_starts_with_name", collectionManager));
        commandManager.set(new AvgOfSpeedCommand("average_of_impact_speed", collectionManager));
        commandManager.set(new ExecuteScriptCommand("execute_script", commandManager));

        InputManager inputManager = new InputManager(commandManager);
        inputManager.run(new ScannerManager(System.in));

    }
}