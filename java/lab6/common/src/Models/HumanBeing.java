package ru.itmo.app.Models;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class HumanBeing implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    private Integer id;
    private String name;
    private Date creationDate;
    private Coordinates coordinates;
    private boolean realHero;
    private Boolean hasToothpick;
    private Long impactSpeed;
    private WeaponType weaponType;
    private Mood mood;
    private Car car;

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = Objects.requireNonNull(coordinates);
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

    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
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
}