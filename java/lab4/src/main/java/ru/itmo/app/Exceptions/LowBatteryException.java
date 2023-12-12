package ru.itmo.app.Exceptions;

public class LowBatteryException extends RuntimeException {
    public LowBatteryException(String message) {
        super(message);
    }
}
