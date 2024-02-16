package ru.itmo.app.Models;

import java.util.Date;
import java.util.Objects;
public class HumanBeing implements Comparable<HumanBeing> {
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private java.util.Date creationDate;
    private boolean realHero;
    private Boolean hasToothpick;
    private Long impactSpeed;
    private WeaponType weaponType;
    private Mood mood;
    private Car car;

    public void setId(Integer id) {
        if (id <= 0) throw new IllegalArgumentException();
        this.id = Objects.requireNonNull(id);
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = Objects.requireNonNull(creationDate);
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public void setCoordinates(Coordinates coordinates)  {
        this.coordinates = Objects.requireNonNull(coordinates);
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

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
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

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public Mood getMood() {
        return mood;
    }

    public Car getCar() {
        return car;
    }
    public Long getImpactSpeed() {
        return impactSpeed;
    }
    public int compareTo(HumanBeing anotherHuman) {
        return this.getId() - anotherHuman.getId();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HumanBeing that = (HumanBeing) o;
        return realHero == that.realHero && Objects.equals(id, that.id)
                && Objects.equals(name, that.name) && Objects.equals(coordinates, that.coordinates)
                && Objects.equals(creationDate, that.creationDate) && Objects.equals(hasToothpick, that.hasToothpick)
                && Objects.equals(impactSpeed, that.impactSpeed) && weaponType == that.weaponType && mood == that.mood
                && Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, realHero, hasToothpick, impactSpeed, weaponType, mood, car);
    }

    @Override
    public String toString() {
        return "HumanBeing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", realHero=" + realHero +
                ", hasToothpick=" + hasToothpick +
                ", impactSpeed=" + impactSpeed +
                ", weaponType=" + weaponType +
                ", mood=" + mood +
                ", car=" + car +
                '}';
    }
}