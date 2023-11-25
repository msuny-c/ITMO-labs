package ru.itmo.app.Things;
import ru.itmo.app.Common.Essence;
import ru.itmo.app.Interfaces.IJump;
import ru.itmo.app.Interfaces.ILocated;
import ru.itmo.app.Places.Place;
import java.util.Objects;
public abstract class Thing extends Essence implements ILocated {
    protected Thing(String name) {
        super(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Thing thing = (Thing) obj;
        return getName().equals(thing.getName()) && this.getLocation() == thing.getLocation();
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