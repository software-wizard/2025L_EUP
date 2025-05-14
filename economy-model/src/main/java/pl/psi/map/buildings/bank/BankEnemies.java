package pl.psi.map.buildings.bank;

import lombok.Getter;
import pl.psi.Point;
import pl.psi.creatures.CreatureStatistic;

import java.util.List;

public enum BankEnemies {
    EASY(List.of(
            new BankCreature(CreatureStatistic.ZOMBIE, new Point(5,5)),
            new BankCreature(CreatureStatistic.BLACK_KNIGHT, new Point(6,6)),
            new BankCreature(CreatureStatistic.SKELETON, new Point(7,7))
    )),
    MEDIUM(List.of(
            new BankCreature(CreatureStatistic.BONE_DRAGON, new Point(5,5)),
            new BankCreature(CreatureStatistic.LICH, new Point(4,4))
    ));

    @Getter
    private final List<BankCreature> creatures;

    BankEnemies(List<BankCreature> creatures){
        this.creatures = creatures;
    }

}
