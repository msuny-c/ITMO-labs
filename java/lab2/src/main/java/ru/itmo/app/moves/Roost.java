package ru.itmo.app.moves;
import ru.ifmo.se.pokemon.*;

public class Roost extends StatusMove {
    public Roost() {
        super(Type.FLYING, 0, 100);
    }
    @Override
    protected void applySelfEffects(Pokemon p) {
        p.setMod(Stat.HP, (int) -(p.getStat(Stat.HP) / 2));
    }
    @Override
    protected String describe() {
        return "used Roost move";
    }
}
