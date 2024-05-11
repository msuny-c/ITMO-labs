package ru.itmo.app.Commands;

import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;

import java.util.NoSuchElementException;

public class AvgOfSpeedCommand extends AbstractCommand {
    public AvgOfSpeedCommand(String name) {
        super(name);
    }

    @Override
    public String execute(CollectionManager collectionManager, HumanBeing object, String[] args) {
        if (collectionManager.getCollection().isEmpty()) return "Collection is empty!";
        var count = collectionManager.getCollection().stream().filter(o -> o.impactSpeed() != null).count();
        var sum = collectionManager.getCollection().stream().filter(o -> o.impactSpeed() != null).mapToLong(HumanBeing::impactSpeed).sum();
        if (count == 0 && !collectionManager.getCollection().isEmpty()) return "There are no objects with numeric ImpactSpeed.";
        var average = sum / (double) count;
        if (count == 0) average = 0;
        return "Average of ImpactSpeed: " + average;
    }

    @Override
    public int numOfArgs() {
        return 0;
    }
}
