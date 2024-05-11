package ru.itmo.app.Commands;

import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class MaxBySpeed extends AbstractCommand {
    public MaxBySpeed(String name) {
        super(name);
    }

    @Override
    public String execute(CollectionManager collectionManager, HumanBeing object, String[] args) {
        try {
            var max = collectionManager.getCollection().stream().filter(o -> o.impactSpeed() != null).max(Comparator.comparing(HumanBeing::impactSpeed)).get();
            return max.toString();

        } catch (NoSuchElementException exception) {
            if (!collectionManager.getCollection().isEmpty()) return "There are no objects with a numeric ImpactSpeed.";
            return "Collection is empty.";
        }
    }

    @Override
    public int numOfArgs() {
        return 0;
    }
}
