package ru.itmo.app.pokemons;
import ru.ifmo.se.pokemon.*;
import ru.itmo.app.moves.*;
public class Wingull extends Pokemon {
    public Wingull(String name, int level) {
        super(name, level);
        setType(Type.WATER, Type.FLYING);
        setStats(40, 30, 30, 55, 30, 85);
        setMove(new Roost(), new Scald(), new WingAttack());
    }
}
