package ru.itmo.app.Models;


import java.io.Serial;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Coordinates implements Serializable, Comparable<Coordinates> {
    @Serial
    private static final long serialVersionUID = 6121685098267759690L;
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

    @Override
    public int compareTo(Coordinates o) {
        if (x > o.x) return 1;
        else if (x == o.x) {
            if (y > o.y) return 1;
            else if (Objects.equals(y, o.y)) return 0;
            else return -1;
        } else return -1;
    }
}