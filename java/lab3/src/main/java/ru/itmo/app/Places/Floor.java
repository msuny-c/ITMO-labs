package ru.itmo.app.Places;
import ru.itmo.app.Interfaces.ILocated;
import ru.itmo.app.Things.*;
import java.util.ArrayList;
public class Floor extends Place implements ILocated {
    ArrayList<Thing> things = new ArrayList<>();
    public Floor(String name) {
        super(name);
    }
    public void describeLocation() {
        System.out.println(getName() + " находится в: " + getLocation().getName());
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
