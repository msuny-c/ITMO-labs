package ru.itmo.app.Things;

import ru.itmo.app.Exceptions.InvalidBatteryException;
import ru.itmo.app.Exceptions.LowBatteryException;

public class Flashlight extends Thing {
    private int battery;
    public Flashlight(String name, int battery) throws InvalidBatteryException {
        super(name);
        if (battery >= 0) {
            this.battery = battery;
        } else throw new InvalidBatteryException("Invalid level of battery");
    }
    public int getBattery() {
        return battery;
    }
    public void setModBattery(int amount) {
        battery += amount;
    }
    public void light(String something) throws LowBatteryException {
        System.out.println(getName() + " высветил " + something);
        if (getBattery() <= 0) {
            throw new LowBatteryException("Flashlight has too low battery");
        } else {
            setModBattery(-1); }
    }
}
