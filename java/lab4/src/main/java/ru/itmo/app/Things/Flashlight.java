package ru.itmo.app.Things;

import ru.itmo.app.Exceptions.InvalidBatteryException;
import ru.itmo.app.Exceptions.LowBatteryException;

public class Flashlight extends Thing {
    private int battery;
    
    public Flashlight(String name, int battery) throws InvalidBatteryException {
        super(name);
        if (battery >= 0) {this.battery = battery;} 
        else throw new InvalidBatteryException("Invalid level of battery");
    }
    
    public int getBattery() {
        return this.battery;
    }
    private void setModBattery(int amount) {
        this.battery += amount;
    }
    public void turnLight(String something) throws LowBatteryException {
        System.out.println(getName() + " высветил " + something);
        if (this.battery > 0) {setModBattery(-1);}
        else throw new LowBatteryException("Flashlight has too low battery to use");
    }
}
