package ru.itmo.app.moves;
import ru.ifmo.se.pokemon.*;
public class Charm extends StatusMove {
    public Charm() {
        super(Type.FAIRY, 0, 100);
    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        p.setMod(Stat.ATTACK, -2);
    }
    @Override
    protected String describe() {
        return "used Charm move";
    }
}
