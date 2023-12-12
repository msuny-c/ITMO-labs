package ru.itmo.app.Places;

import ru.itmo.app.Interfaces.ILocated;

public class Doorway extends Place implements ILocated {
    private int width;
    private int length;
    public Doorway(String name) {
        super(name);
    }
    public void describeLocation() {
        System.out.println(getName() + " находится в: " + getLocation().getName());
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public String describe() {
        return getName() + " c шириной " + width + " футов " + "и длиной " + length + " футов";
    }
}
