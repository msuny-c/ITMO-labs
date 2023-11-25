package ru.itmo.app.Persons;
import ru.itmo.app.Interfaces.ILocated;
import ru.itmo.app.Places.Place;
import ru.itmo.app.Common.Essence;

import java.util.Objects;

public abstract class Entity extends Essence implements ILocated {
    public Entity(String name) {
        super(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Entity entity = (Entity) obj;
        return getName().equals(entity.getName()) && getLocation() == entity.getLocation();
    }
    @Override
    public String toString() {
        if (getLocation() != null) {
            return getClass() + "{" + "name=" + getName() +
                    ", location=" + getLocation().getName() + "}";
        } else {
            return getClass() + "{" + "name=" + getName() +
                    ", location=null}";}
    }
    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
