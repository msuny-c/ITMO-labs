package ru.itmo.app;
import ru.ifmo.se.pokemon.*;
import ru.itmo.app.pokemons.*;
public class App {
    public static void main(String[] args) {
        Battle battle = new Battle();

        battle.addAlly(new Mesprit("Voldemort", 4));
        battle.addAlly(new Kirlia("Klimenkov", 5));
        battle.addAlly(new Gardevoir("Petya", 3));

        battle.addFoe(new Pelipper("Harry Potter", 4));
        battle.addFoe(new Ralts("Darth Vader", 3));
        battle.addFoe(new Wingull("Afanas", 6));

        battle.go();
    }
}
