package ru.itmo.app.pokemons;
import ru.ifmo.se.pokemon.*;
import ru.itmo.app.moves.*;

public class Pelipper extends Wingull {
    public Pelipper(String name, int level) {
        super(name, level);
        setStats(60, 50, 100, 95, 70, 65);
        addMove(new HydroPump());
    }
}
