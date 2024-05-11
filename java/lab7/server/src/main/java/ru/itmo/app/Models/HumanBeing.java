package ru.itmo.app.Models;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class HumanBeing implements Serializable, Comparable<HumanBeing> {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    private Integer id;
    private String name;
    private Date creationDate;
    private Coordinates coordinates;
    private Mood mood;
    private WeaponType weaponType;
    private Car car;
    private boolean realHero;
    private Boolean hasToothpick;
    private Long impactSpeed;
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = Objects.requireNonNull(coordinates);
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public void setCar(Car car) {
        this.car = Objects.requireNonNull(car);
    }

    public void setRealHero(boolean realHero) {
        this.realHero = realHero;
    }

    public void setHasToothpick(Boolean hasToothpick) {
        this.hasToothpick = hasToothpick;
    }

    public void setImpactSpeed(Long impactSpeed) {
        this.impactSpeed = impactSpeed;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer id() {
        return id;
    }

    public Coordinates coordinates() {
        return coordinates;
    }

    public String name() {
        return name;
    }

    public Long impactSpeed() {
        return impactSpeed;
    }
    public Mood mood() {
        return mood;
    }
    public WeaponType weaponType() {
        return weaponType;
    }
    public boolean realHero() {
        return realHero;
    }
    public Boolean hasToothPick() {
        return hasToothpick;
    }
    public Car car() {
        return car;
    }
    public void update(HumanBeing object) {
        name = object.name;
        coordinates = object.coordinates;
        realHero = object.realHero;
        hasToothpick = object.hasToothpick;
        weaponType = object.weaponType;
        mood = object.mood;
        car = object.car;
        impactSpeed = object.impactSpeed;
    }
    public int compareTo(HumanBeing anotherHuman) {
        return this.name.compareTo(anotherHuman.name);
    }
    @Override
    public String toString() {
        return "HumanBeing(" +
                "id=" + id +
                ", name='" + name + "'" +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", realHero=" + realHero +
                ", hasToothpick=" + hasToothpick +
                ", impactSpeed=" + impactSpeed +
                ", weaponType=" + weaponType +
                ", mood=" + mood +
                ", car=" + car + ")";
    }
}