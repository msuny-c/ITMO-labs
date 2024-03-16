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
        try {
            var count = collectionManager.getCollection().stream().filter(o -> o.getImpactSpeed() != null).count();
            var sum = collectionManager.getCollection().stream().filter(o -> o.getImpactSpeed() != null).mapToLong(HumanBeing::getImpactSpeed).sum();
            var average = sum / (double) count;
            if (count == 0) average = 0;
            return "Average of ImpactSpeed: " + average;
        } catch (NoSuchElementException exception) {
            return "Collection is empty or there is no object with numeric value";
        }
    }

    @Override
    public int numOfArgs() {
        return 0;
    }
}
