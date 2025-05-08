package pl.psi.map.buildings;

import pl.psi.Point;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatistic;
import pl.psi.hero.EconomyHero;
import pl.psi.map.resources.Resources;

import java.util.HashMap;
import java.util.Map;

public class Bank implements BuildingIf{
    final Resources prize;
    final Map<Point, Creature> enemies;

    public Bank(Resources prize) {
        this.prize = prize;
        this.enemies = createCreaturesOnPoints();
    }

    public Map<Point, Creature> getEnemies(){
        return  enemies;
    }

    public static Map<Point, Creature> createCreaturesOnPoints() {
        Map<Point, Creature> creatureMap = new HashMap<>();

        creatureMap.put(new Point(4, 5), new Creature.Builder()
                .statistic(CreatureStatistic.SKELETON)
                .amount(20)
                .build());

        creatureMap.put(new Point(5, 6), new Creature.Builder()
                .statistic(CreatureStatistic.BLACK_KNIGHT)
                .amount(5)
                .build());

        creatureMap.put(new Point(6, 5), new Creature.Builder()
                .statistic(CreatureStatistic.ZOMBIE)
                .amount(10)
                .build());

        creatureMap.put(new Point(5, 4), new Creature.Builder()
                .statistic(CreatureStatistic.LICH)
                .amount(3)
                .build());

        creatureMap.put(new Point(6, 6), new Creature.Builder()
                .statistic(CreatureStatistic.BONE_DRAGON)
                .amount(2)
                .build());

        return creatureMap;
    }

    @Override
    public String getPath() {
        return "/objects/goldPile1.png";
    }

    @Override
    public void endOfTurn() {

    }

    @Override
    public void enter(EconomyHero hero) {

    }

    @Override
    public void generateResource() {

    }

    @Override
    public void interact(EconomyHero hero, Point point) {

    }

    @Override
    public typeOfObject getTypeOfObject() {
        return null;
    }

    @Override
    public EconomyHero getOwner() {
        return null;
    }

    @Override
    public EnterAction onEnter() {
        return new EnterAction(EnterActionType.ENTER_BANK, this);
    }

    @Override
    public EnterAction secondInteraction() {
        return null;
    }
}
