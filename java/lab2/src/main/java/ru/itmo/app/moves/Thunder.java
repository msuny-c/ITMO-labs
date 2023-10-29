package ru.itmo.app.moves;
import ru.ifmo.se.pokemon.*;

public class Thunder extends SpecialMove {
    public Thunder() {
        super(Type.ELECTRIC, 110, 70);
    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        if (0.3 > Math.random()) {
            Effect.paralyze(p);
        }
    }
    @Override
    protected String describe() {
        return "used Thunder move";
    }
}
