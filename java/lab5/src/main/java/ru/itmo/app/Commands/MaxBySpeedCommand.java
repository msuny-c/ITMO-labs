package ru.itmo.app.Commands;

import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;
import ru.itmo.app.Managers.ScannerManager;

import java.util.NoSuchElementException;

/**
 * Class for command 'max_by_impact_speed'
 * displays an object which impactSpeed value is maximum.
 */
public class MaxBySpeedCommand extends CollectionCommand {
    public MaxBySpeedCommand(String name, CollectionManager collectionManager) {
        super(name, collectionManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        try {
            if (collectionManager.getCollection().isEmpty()) throw new NoSuchElementException();
            Long maxSpeed = null;
            for (HumanBeing object : collectionManager.getCollection()) {
                if (maxSpeed == null && object.getImpactSpeed() != null) maxSpeed = object.getImpactSpeed();
                if (maxSpeed != null && object.getImpactSpeed() != null) {
                    if (object.getImpactSpeed() > maxSpeed) maxSpeed = object.getImpactSpeed();
                }
            }
            if (maxSpeed == null) throw new NullPointerException();
            System.out.println(maxSpeed);
        } catch (NoSuchElementException exception) {
            System.out.println("Collection is empty!");
        } catch (NullPointerException exception) {
            System.out.println("There is no object with numeric value");
        }
    }

    @Override
    public int numOfArgs() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "displays any object from the collection which impactSpeed field value is maximum";
    }
}
