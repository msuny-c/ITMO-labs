package ru.itmo.app.Things;

import ru.itmo.app.Exceptions.InvalidBatteryException;
import ru.itmo.app.Exceptions.LowBatteryException;

public class Flashlight extends Thing {
    private int battery;
    
    public Flashlight(String name, int battery) throws InvalidBatteryException {
        super(name);
        if (battery >= 0) {this.battery = battery;} 
        else throw new InvalidBatteryException("Invalid level of battery: " + battery);
    }
    
    public int getBattery() {
        return battery;
    }
    private void setModBattery(int amount) {
        battery += amount;
    }
    public void turnLight(String something) throws LowBatteryException {
        if (battery > 0) {setModBattery(-1);}
        else throw new LowBatteryException("Flashlight has too low battery to use: " + battery);
        System.out.println(getName() + " высветил " + something);
    }
}
