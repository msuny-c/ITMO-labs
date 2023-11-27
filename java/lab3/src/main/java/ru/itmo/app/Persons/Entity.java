package ru.itmo.app.Persons;
import ru.itmo.app.Interfaces.ILocated;
import ru.itmo.app.Common.Essence;

import java.util.Objects;

public abstract class Entity extends Essence implements ILocated {
    public Entity(String name) {
        super(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(getName(), entity.getName());
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
