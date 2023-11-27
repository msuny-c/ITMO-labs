package ru.itmo.app.Persons;
import ru.itmo.app.Interfaces.*;
import ru.itmo.app.Places.*;
public class Narrator extends Entity implements IFind, IClimb, IGo, IJump, IDoubt {
    public Narrator() {
        super("мы");
    }
    
    public void go(Place place) {
        setLocation(place);
        System.out.println(getName() + " вошли в " + place.getName());
    }
    public void climb(Climbable climbable) {
        climbable.beClimbedBy(this);
    }
    public void jump(Place place) {
        System.out.println(getName() + " прыгнули на " + place.getName());
    }
    public void notScared(String something) {
        System.out.println(getName() + " не побоялись " + something);
    }
    public void doubt() {
        System.out.println("нас охватило сомнение");
    }
    public void find(String something) {
        System.out.println(getName() + " отыскали " + something);
    }
}
