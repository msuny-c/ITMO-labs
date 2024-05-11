package ru.itmo.app;

import com.google.common.hash.Hashing;
import ru.itmo.app.Commands.*;
import ru.itmo.app.Managers.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        HashManager hashManager = new HashManager();
        hashManager.generateSalt();
        AuthManager authManager = new AuthManager(hashManager);
        DatabaseManager databaseManager = new DatabaseManager("s408194","GsVXRpw7kHqtNIp6");
        authManager.setConnection(databaseManager.getConnection());
        CollectionManager collectionManager = new CollectionManager(databaseManager);
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

        CommandHandler commandHandler = new CommandHandler(collectionManager, commandManager, authManager);
        ServerManager serverManager = new ServerManager(9142, commandHandler);
        serverManager.run();

    }
}