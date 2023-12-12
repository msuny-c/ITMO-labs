package ru.itmo.app.Places;

import ru.itmo.app.Interfaces.ILocated;
import ru.itmo.app.Things.Slippers;
import ru.itmo.app.Things.Thing;

import java.util.ArrayList;

public class Building extends Place {
    public Building(String name)
    {
        super(name);
    }
    public void faces(String direction) {
        System.out.println(getName() + " было обращено на " + direction);
    }
    public class Wall extends Place implements ILocated {
        public Wall(String name) {
            super(name);
        }
    }
    public class Floor extends Place implements ILocated {
        private final ArrayList<Thing> things = new ArrayList<>();
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
}
