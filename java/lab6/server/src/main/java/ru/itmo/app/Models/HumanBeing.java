package ru.itmo.app.Models;

import java.io.Serializable;
import java.util.Date;

public class HumanBeing implements Serializable, Comparable<HumanBeing> {
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
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public void setCar(Car car) {
        this.car = car;
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

    public Integer getId() {
        return id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Mood getMood() {
        return mood;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public Car getCar() {
        return car;
    }

    public String getName() {
        return name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public boolean isRealHero() {
        return realHero;
    }

    public Boolean getHasToothpick() {
        return hasToothpick;
    }

    public Long getImpactSpeed() {
        return impactSpeed;
    }
    public void update(HumanBeing object) {
        name = object.name;
        coordinates = object.coordinates;
        realHero = object.realHero;
        hasToothpick = object.hasToothpick;
        weaponType = object.weaponType;
        mood = object.mood;
        car = object.car;
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