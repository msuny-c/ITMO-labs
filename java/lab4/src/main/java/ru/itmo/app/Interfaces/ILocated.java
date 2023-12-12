package ru.itmo.app.Interfaces;

import ru.itmo.app.Places.Place;
import java.util.HashMap;

public interface ILocated {
    class Location {
        private static final HashMap<ILocated, Place> location = new HashMap<>();
        private static Place getLocation(ILocated object) {
            return location.get(object);
        }
        private static void setLocation(ILocated object, Place place) {
            location.put(object, place);
        }
    }
    default void setLocation(Place place) { Location.setLocation(this, place); }
    default Place getLocation() {
        return Location.getLocation(this);
    }
}
