package ru.itmo.app.moves;
import ru.ifmo.se.pokemon.*;
public class Hypnosis extends StatusMove {
    public Hypnosis() {
        super(Type.PSYCHIC, 0, 60);
    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        Effect.sleep(p);
    }
    @Override
    protected String describe() {
        return "used Hypnosis move";
    }
}
