package Places;
public class SinglePlace extends Place {
    private Place location;
    public SinglePlace(String name) {
        super(name);
    }
    public void setLocation(Place place) {
        this.location = place;
    }
    public void getLocation() {
        System.out.println("расположение " + getName() + ": " + location.getName());
    }
}
