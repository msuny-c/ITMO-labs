package ru.itmo.app.Utilities;

import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Managers.ScannerManager;
import ru.itmo.app.Models.*;

import java.io.EOFException;
import java.util.*;

/**
 * Class to read HumanBeing object from user's input.
 */
public class HumanGetter {
    private final CollectionManager collectionManager;
    private final String[] args;
    private final ScannerManager scannerManager;

    public HumanGetter(String[] args, CollectionManager collectionManager, ScannerManager scannerManager) {
        this.args = args;
        this.collectionManager = collectionManager;
        this.scannerManager = scannerManager;
    }

    /**
     * Parses boolean value from a string.
     * @param string string to parse.
     * @return boolean value.
     */
    private Boolean parseBoolean(String string) {
        if (string.equalsIgnoreCase("true")) return Boolean.TRUE;
        if (string.equalsIgnoreCase("false")) return Boolean.FALSE;
        throw new NumberFormatException();
    }

    /**
     * Sets HumanBeing fields from args.
     */
    private void setHuman(HumanBeing object) {
        int i = 0;
        if (args.length < 2) throw new IllegalArgumentException();
        object.setName(args[i++]);
        object.setRealHero(parseBoolean(args[i++]));
        try {
            object.setHasToothpick(parseBoolean(args[i]));
            i++;
        } catch (IllegalArgumentException | IndexOutOfBoundsException exception) {
            object.setHasToothpick(null);
        }
        try {
            object.setImpactSpeed(Long.valueOf(args[i]));
        } catch (IndexOutOfBoundsException exception) {
            object.setImpactSpeed(null);
        }
    }

    /**
     * Gets Mood field from user's input
     * @return Mood object.
     */
    private Mood getMood() {
        while (true) {
            System.out.println("Enter Mood field (SORROW, LONGING, APATHY, CALM, FRENZY):");
            try {
                String value = scannerManager.nextLine().trim();
                if (value.isEmpty()) return null;
                return Mood.valueOf(value);
            } catch (IllegalArgumentException exception) {
                System.out.println("You have entered invalid values. Please try again");
            }
        }
    }

    /**
     * Gets WeaponType field from user's input
     * @return WeaponType object.
     */
    private WeaponType getWeaponType()  {
        while (true) {
            System.out.println("Enter WeaponType field (RIFLE, KNIFE, MACHINE_GUN, BAT):");
            try {
                String value = scannerManager.nextLine().trim();
                if (value.isEmpty()) return null;
                return WeaponType.valueOf(value);
            } catch (IllegalArgumentException exception) {
                System.out.println("You have entered invalid values. Please try again");
            }
        }
    }

    /**
     * Sets coordinates to HumanBeing object with user's input
     */
    private void setCoordinates(HumanBeing object)  {
        while (true) {
            try {
                System.out.println("Enter Coordinates object. Syntax: {x} {y}");
                String inputLine = scannerManager.nextLine();
                if (inputLine.trim().isEmpty()) {object.setCoordinates(null); return;}
                String[] coordinatesArgs = inputLine.split(" +");
                if (coordinatesArgs.length > 2) throw new IllegalArgumentException();
                Coordinates coordinates = new Coordinates();
                coordinates.setX(Float.parseFloat(coordinatesArgs[0]));
                coordinates.setY(Long.parseLong(coordinatesArgs[1]));
                object.setCoordinates(coordinates);
                return;
            } catch (IndexOutOfBoundsException | IllegalArgumentException exception) {
                System.out.println("Incorrect arguments. It should be: {x} {y}");
            } catch (NullPointerException exception) {
                System.out.println("This value cannot be null. Please try again");
            }
        }
    }

    /**
     * Gets Car field from user's input
     */
    private void setCar(HumanBeing object) {
        while (true) {
            try {
                System.out.println("Please enter Car object. Syntax: {name} {cool}");
                String inputLine = scannerManager.nextLine();
                if (inputLine.trim().isEmpty()) {object.setCar(null); return;}
                String[] carArgs = inputLine.split(" +");
                Car car = new Car();
                car.setName(carArgs[0]);
                object.setCar(car);
                car.setCool(parseBoolean(carArgs[1])); return;
            } catch (IndexOutOfBoundsException | IllegalArgumentException exception) {
                System.out.println("Incorrect arguments. It should be: {name} {cool}");
            } catch (NullPointerException exception) {
                System.out.println("This value cannot be null. Please try again");
            }
        }
    }

    /**
     * Searches free id in the collection.
     * @return free id.
     */
    private int getFreeId() {
        TreeSet<Integer> idOfHumans = new TreeSet<>();
        for (HumanBeing humanBeing : collectionManager.getCollection()) {
            if (humanBeing.getId() != null) idOfHumans.add(humanBeing.getId());
        }
        int fid = 1;
        if (!idOfHumans.isEmpty()) fid = idOfHumans.first();
        for (int id : idOfHumans) {
            if (fid != id) break;
            fid++;
        }
        return fid;
    }

    /**
     * Constructs new HumanBeing object.
     * @return HumanBeing object.
     */
    public HumanBeing getHuman() {
        HumanBeing newHuman = new HumanBeing();
        newHuman.setId(getFreeId());
        setHuman(newHuman);
        setCoordinates(newHuman);
        newHuman.setMood(getMood());
        newHuman.setWeaponType(getWeaponType());
        setCar(newHuman);
        return newHuman;
    }

}
