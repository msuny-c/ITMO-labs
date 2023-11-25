package Persons;
import Places.Place;
import Common.Essence;
import Things.Thing;

import java.util.Objects;

public abstract class Entity extends Essence {
    public Entity(String name) {
        super(name);
    }
    private Place location;
    public void setLocation(Place location) {
        this.location = location;
    }
    public String getLocation() {
        return this.location.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Entity entity = (Entity) obj;
        return getName().equals(entity.getName()) && this.location == entity.location;
    }
    @Override
    public String toString() {
        if (location != null) {
            return getClass() + "{" + "name=" + getName() +
                    ", location=" + location.getName() + "}";
        } else {
            return getClass() + "{" + "name=" + getName() +
                    ", location=null}";}
    }
    @Override
    public int hashCode() {
        return Objects.hash(getName(), location);
    }
}
