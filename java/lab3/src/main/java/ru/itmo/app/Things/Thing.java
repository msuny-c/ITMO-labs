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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Thing thing = (Thing) o;
        return Objects.equals(getName(), thing.getName());
    }
    @Override
    public String toString() {
        return getClass().getName() + "{name=" + getName() + "}";
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(getName());
    }
}
