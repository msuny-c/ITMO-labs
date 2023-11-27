package ru.itmo.app;
import ru.itmo.app.Places.*;
import ru.itmo.app.Persons.*;
import ru.itmo.app.Things.*;
import ru.itmo.app.Common.Directions;
public class Main {
    public static void main(String[] args) {
        Narrator narrator = new Narrator();

        Building firstBuilding = new Building("строение");
        Doorway doorway = new Doorway("арочный проем");
        Wall wall = new Wall("стена");

        doorway.setLocation(wall);
        doorway.setWidth("шесть");
        doorway.setLength("десять футов");

        narrator.find(doorway.describe());
        doorway.getLocation();

        Lobby lobby = new Lobby("вестибюль с барельефными портретами по стенам");
        Floor floor = new Floor("пол");
        floor.setLocation(lobby);
        floor.saved(Directions.HERE.getDescribe());

        firstBuilding.faces(Directions.WEST.getDescribe());

        Building secondBuilding = new Building("обшарпанная глухая постройка");
        secondBuilding.stands(Directions.OPPOSITE.getDescribe());

        Building house = new Building("дом");
        Debris debris = new Debris("гора обломков");
        debris.makeEasier("вход в дом");

        narrator.doubt();
        narrator.notScared("влиться в эту стародавнюю мистерию");
        narrator.go(house);

        Stones stones = new Stones("обледенелые камни");
        narrator.climb(stones);

        Slippers slippers = new Slippers("сланцы");
        floor.addThings(slippers);

        narrator.jump(floor);

        floor.getThings();
        floor.getLocation();
    }
}
