package ru.itmo.app.moves;
import ru.ifmo.se.pokemon.*;
public class ShadowBall extends SpecialMove {
    public ShadowBall() {
        super(Type.GHOST, 80, 100);
    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        if (0.2 > Math.random()) {
            p.setMod(Stat.SPECIAL_DEFENSE, -1);
        }
    }
    @Override
    protected String describe() {
        return "used Shadow Ball move";
    }
}
