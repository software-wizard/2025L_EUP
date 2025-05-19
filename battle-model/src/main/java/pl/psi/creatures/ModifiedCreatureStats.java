package pl.psi.creatures;

import com.google.common.collect.Range;

public class ModifiedCreatureStats implements CreatureStatisticIf {

    private final CreatureStatisticIf baseStats;
    private final StatsModifier bonusStats;

    public ModifiedCreatureStats(CreatureStatisticIf base, StatsModifier bonus) {
        this.baseStats = base;
        this.bonusStats = bonus;
    }

    @Override
    public int getAttack() {
        return baseStats.getAttack() + bonusStats.getAttackBonus();
    }

    @Override
    public int getArmor() {
        return baseStats.getArmor() + bonusStats.getArmorBonus();
    }

    @Override
    public int getMaxHp() {
        return baseStats.getMaxHp();
    }

    @Override
    public int getMoveRange() {
        return baseStats.getMoveRange();
    }

    @Override
    public Range<Integer> getDamage() {
        return baseStats.getDamage();
    }

    @Override
    public String getName() {
        return baseStats.getName();
    }

    @Override
    public int getTier() {
        return baseStats.getTier();
    }

    @Override
    public String getDescription() {
        return baseStats.getDescription();
    }

    @Override
    public boolean isUpgraded() {
        return baseStats.isUpgraded();
    }
}
