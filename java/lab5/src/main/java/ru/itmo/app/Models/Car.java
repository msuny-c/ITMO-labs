package ru.itmo.app.Models;


import java.util.Objects;

public class Car {
    private String name;
    private boolean cool;

    public String getName() {
        return name;
    }

    public boolean isCool() {
        return cool;
    }

    public void setName(String name) {
        if (name.isEmpty()) throw new IllegalArgumentException();
        this.name = Objects.requireNonNull(name);
    }

    public void setCool(boolean cool) {
        this.cool = cool;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", cool=" + cool +
                '}';
    }
}
