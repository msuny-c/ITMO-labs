package ru.itmo.app.Places;

import ru.itmo.app.Interfaces.ILocated;

public class Doorway extends Place implements ILocated {
    private String width;
    private String length;
    public Doorway(String name) {
        super(name);
    }
    public void setWidth(String width) {
        this.width = width;
    }
    public void setLength(String length) {
        this.length = length;
    }
    public String describe() {
        return getName() + " c шириной " + width + " и длиной " + length;
    }
}
