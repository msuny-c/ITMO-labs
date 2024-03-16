package ru.itmo.app.Utilities;

import ru.itmo.app.Managers.CollectionManager;
import ru.itmo.app.Models.HumanBeing;

import java.util.Date;
import java.util.TreeSet;

public class HumanSetter {
    public static void setId(HumanBeing object, CollectionManager collectionManager) {
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
        object.setId(fid);
    }
    public static void setCreationDate(HumanBeing object) {
        object.setCreationDate(new Date());
    }
}
