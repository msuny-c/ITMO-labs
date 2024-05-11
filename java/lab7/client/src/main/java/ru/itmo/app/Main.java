package ru.itmo.app;

import ru.itmo.app.Commands.*;
import ru.itmo.app.Managers.CommandManager;
import ru.itmo.app.Managers.InputManager;
import ru.itmo.app.Managers.ScannerManager;
import ru.itmo.app.Managers.ClientManager;

import java.io.*;
import java.net.InetAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        CommandManager commandManager = new CommandManager();
        InputManager inputManager = new InputManager(commandManager);
        InetAddress host = InetAddress.getLocalHost();
        int port = 9142;
        ClientManager clientManager = new ClientManager(host, port);
        clientManager.connect(host, port);

        commandManager.set(new RegisterCommand("register", clientManager));
        commandManager.set(new LoginCommand("login", clientManager));
        commandManager.set(new AddCommand("add", clientManager));
        commandManager.set(new ClearCommand("clear", clientManager));
        commandManager.set(new ReorderCommand("reorder", clientManager));
        commandManager.set(new RemoveByIdCommand("remove_by_id", clientManager));
        commandManager.set(new MaxBySpeedCommand("max_by_impact_speed", clientManager));
        commandManager.set(new ShowCommand("show", clientManager));
        commandManager.set(new UpdateCommand("update", clientManager));
        commandManager.set(new RemoveGreaterCommand("remove_greater", clientManager));
        commandManager.set(new FilterNameCommand("filter_starts_with_name", clientManager));
        commandManager.set(new ExitCommand("exit"));
        commandManager.set(new InfoCommand("info", clientManager));
        commandManager.set(new AddIfMinCommand("add_if_min", clientManager));
        commandManager.set(new AvgOfSpeedCommand("average_of_impact_speed", clientManager));
        commandManager.set(new ExecuteScriptCommand("execute_script", commandManager));
        commandManager.set(new HelpCommand("help", commandManager));

        inputManager.run(new ScannerManager(System.in));
    }
}