package ru.itmo.app.Models;


import java.util.Objects;

public class Coordinates {
    private float x;
    private Long y;

    public float getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    public void setX(float x) {
        if (x > 701) throw new IllegalArgumentException();
        this.x = x;
    }

    public void setY(Long y) {
        this.y = Objects.requireNonNull(y);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}