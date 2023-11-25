package Places;
import Things.*;
import java.util.ArrayList;
public class Floor extends SinglePlace {
    ArrayList<Thing> things = new ArrayList<>();
    public Floor(String name) {
        super(name);
    }
    public void saved(String direction) {
        System.out.println(getName() + " " + direction + " сохранился");
    }
    public void addThings(Slippers thing) {
        things.add(thing);
    }
    public void getThings() {
        System.out.print(getName() + " содержит:");
        for (Thing thing: things) {
            System.out.print(" " + thing.getName());
        }
        System.out.println();
    }
}
