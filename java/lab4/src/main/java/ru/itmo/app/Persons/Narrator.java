package ru.itmo.app.Persons;
import ru.itmo.app.Exceptions.LowBatteryException;
import ru.itmo.app.Interfaces.*;
import ru.itmo.app.Places.*;
import ru.itmo.app.Things.Flashlight;

public class Narrator extends Entity implements IFind, IClimb, IGo, IJump, IDoubt, IDecide {
    private Flashlight flashlight;
    
    public Narrator() {
        super("мы");
    }
    
    public void setFlashlight(Flashlight flashlight) {
        this.flashlight = flashlight;
    }
    public void decide(String something) {
        System.out.println(getName() + " решили " + something);
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
    public void useFlashlight(String something) {
        try {flashlight.turnLight(something);} 
        catch (LowBatteryException e) {System.out.println(e.getMessage());}
        catch (NullPointerException e) {System.out.println("Narrator doesn't have flashlight");}
    }
}
