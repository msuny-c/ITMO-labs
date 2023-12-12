package ru.itmo.app;
import ru.itmo.app.Exceptions.InvalidBatteryException;
import ru.itmo.app.Places.*;
import ru.itmo.app.Persons.*;
import ru.itmo.app.Things.*;
import ru.itmo.app.Common.Directions;
public class Main {
    public static void main(String[] args) {
        Narrator narrator = new Narrator();
        try {
            narrator.setFlashlight(new Flashlight("фонарик", 10));
        } catch (InvalidBatteryException e) {
            System.out.println(e.getMessage());
        }
        Building firstBuilding = new Building("строение");
        Doorway doorway = new Doorway("арочный проем");
        Building.Wall wall = firstBuilding.new Wall("стена");

        doorway.setLocation(wall);
        doorway.setWidth(6);
        doorway.setLength(10);

        Lobby lobby = new Lobby("вестибюль с барельефными портретами по стенам");

        Building.Floor floor = firstBuilding.new Floor("пол");
        floor.setLocation(lobby);

        // Использование анонимного класса
        Building secondBuilding = new Building("обшарпанная глухая постройка") {
            @Override
            public void faces(String direction) {
                System.out.println(getName() + " возвышался " + direction);
            }
        };

        Building house = new Building("дом");
        Debris debris = new Debris("гора обломков");

        Stones stones = new Stones("обледенелые камни");

        Slippers slippers = new Slippers("сланцы");
        floor.addThings(slippers);

        // Использование локального класса
        class Story {
            public void tellStory() {
                narrator.useFlashlight("четкие барельефы с резными портретами");
                narrator.decide("вернуться сюда, если не найдем ничего более доступного");
                narrator.find(doorway.describe());
                doorway.describeLocation();
                floor.saved(Directions.HERE.getDescribe());
                firstBuilding.faces(Directions.WEST.getDescribe());
                secondBuilding.faces(Directions.OPPOSITE.getDescribe());
                debris.makeEasier("вход в дом");
                narrator.doubt();
                narrator.notScared("влиться в эту стародавнюю мистерию");
                narrator.go(house);
                narrator.climb(stones);
                narrator.jump(floor);
                floor.getThings();
                floor.describeLocation();
                narrator.decide("что пора рвать бумагу");
            }
        }
        Story story = new Story();
        story.tellStory();

    }
}
