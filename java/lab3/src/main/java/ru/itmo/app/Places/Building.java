package Places;
public class Building extends OpenPlace {
    public Building(String name)
    {
        super(name);
    }
    public void faces(String direction) {
        System.out.println(getName() + " было обращено на " + direction);
    }
    public void stands(String direction) {
        System.out.println(getName() + " возвышался " + direction);
    }
}
