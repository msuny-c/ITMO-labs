package ru.itmo.app.Places;
import ru.itmo.app.Common.Essence;
import java.util.Objects;

public abstract class Place extends Essence {
    protected Place(String name) {
        super(name);
    }

    @Override
    public boolean equals(Object otherObject)
    {
        if (this == otherObject) return true;
        if (otherObject == null) return false;
        if (!this.getClass().equals(otherObject.getClass())) return false;
        Place other = (Place) otherObject;
        return Objects.equals(getName(), other.getName());
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(getName());
    }
    @Override
    public String toString() {
        return getClass() + "{" + "name=" + getName() + "}";
    }
}
