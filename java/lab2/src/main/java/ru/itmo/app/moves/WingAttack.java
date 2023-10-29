package ru.itmo.app.moves;
import ru.ifmo.se.pokemon.*;

public class WingAttack extends PhysicalMove {
    public WingAttack() {
        super(Type.FLYING, 60, 100);
    }
    @Override
    protected String describe() {
        return "used Wing Attack move";
    }
}
