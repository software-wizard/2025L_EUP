package pl.psi.map.buildings.bank;

import lombok.Getter;
import pl.psi.Point;
import pl.psi.creatures.CreatureStatistic;

public class BankCreature {
    @Getter
    private final CreatureStatistic creature;
    private final Point point;

    public BankCreature(CreatureStatistic creature, Point point){
        this.creature = creature;
        this.point = point;
    }

}
