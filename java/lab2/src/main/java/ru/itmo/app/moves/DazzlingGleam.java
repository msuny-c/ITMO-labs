package ru.itmo.app.moves;
import ru.ifmo.se.pokemon.*;
public class DazzlingGleam extends SpecialMove {
    public DazzlingGleam() {
        super(Type.FAIRY, 80, 100);
    }
    @Override
    protected String describe() {
        return "used Dazzling Gleam move";
    }
}
