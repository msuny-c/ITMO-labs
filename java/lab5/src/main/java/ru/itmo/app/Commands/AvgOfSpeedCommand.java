package ru.itmo.app.Commands;

import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;
import ru.itmo.app.Managers.ScannerManager;

import java.util.NoSuchElementException;

/**
 * Class for command 'average_of_impact_speed'
 * displays average value of impactSpeed field of all elements of the collection
 */
public class AvgOfSpeedCommand extends CollectionCommand {
    public AvgOfSpeedCommand(String name, CollectionManager collectionManager) {
        super(name, collectionManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        try {
            long sum = 0; boolean t = true;
            for (HumanBeing object : collectionManager.getCollection()) {
                if (object.getImpactSpeed() != null) {sum += object.getImpactSpeed(); t = false;}
            }
            double average;
            if (collectionManager.getCollection().isEmpty() || t) throw new NoSuchElementException();
            else average = sum / (double) collectionManager.getCollection().size();
            System.out.println("Average of ImpactSpeed: " + average);
        } catch (NoSuchElementException exception) {
            System.out.println("Collection is empty or there is no object with numeric value");
        }
    }

    @Override
    public int numOfArgs() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "displays the average value of the impactSpeed field of all elements of the collection";
    }
}
