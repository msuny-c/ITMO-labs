package ru.itmo.app.pokemons;
import ru.ifmo.se.pokemon.*;
import ru.itmo.app.moves.*;
public class Gardevoir extends Kirlia {
    public Gardevoir(String name, int level) {
        super(name, level);
        setStats(68, 65, 65, 125, 115, 80);
        addMove(new ShadowBall());
    }
}
