package ru.itmo.app.moves;
import ru.ifmo.se.pokemon.*;

public class Scald extends SpecialMove {
    public Scald() {
        super(Type.WATER, 80, 100);
    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        if (0.3 > Math.random()) {
            Effect.burn(p);
        }
    }
    @Override
    protected String describe() {
        return "used Scald move";
    }
}
