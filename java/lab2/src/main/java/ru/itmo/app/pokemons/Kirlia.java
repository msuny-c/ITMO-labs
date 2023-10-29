package ru.itmo.app.pokemons;
import ru.ifmo.se.pokemon.*;
import ru.itmo.app.moves.*;
public class Kirlia extends Ralts {
    public Kirlia(String name, int level) {
        super(name, level);
        setStats(38, 35, 35, 65, 55, 50);
        addMove(new Charm());
    }
}
