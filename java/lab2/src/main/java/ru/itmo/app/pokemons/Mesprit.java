package ru.itmo.app.pokemons;
import ru.ifmo.se.pokemon.*;
import ru.itmo.app.moves.*;
public class Mesprit extends Pokemon {
    public Mesprit(String name, int level) {
        super(name, level);
        setType(Type.PSYCHIC);
        setStats(80, 105, 105, 105, 105, 80);
        setMove(new DazzlingGleam(), new Psychic(), new Thunder(), new DoubleTeam());
    }
}
