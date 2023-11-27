package ru.itmo.app.Places;
import ru.itmo.app.Common.Essence;
import java.util.Objects;

public abstract class Place extends Essence {
    protected Place(String name) {
        super(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Objects.equals(getName(), place.getName());
    }
    @Override
    public String toString() {
        return getClass().getName() + "{name=" + getName() + "}";
    }
    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
