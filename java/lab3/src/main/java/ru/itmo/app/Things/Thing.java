package Things;
import Common.Essence;
import Places.Place;
import java.util.Objects;
public abstract class Thing extends Essence {
    private Place location;
    protected Thing(String name) {
        super(name);
    }
    public void setLocation(Place place) {
        this.location = place;
    }
    public void getLocation() {
        System.out.println(this.location.getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Thing thing = (Thing) obj;
        return getName().equals(thing.getName()) && this.location == thing.location;
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
        return Objects.hash(getName());
    }
}