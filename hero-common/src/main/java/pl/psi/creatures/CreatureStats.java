package pl.psi.creatures;

import com.google.common.collect.Range;

import lombok.*;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@RequiredArgsConstructor
@Builder
public class CreatureStats implements CreatureStatisticIf{
    private final String name;
    private final int attack;

    private final int armor;
    private final int maxHp;
    private final int moveRange;
    private final Range< Integer > damage;
    private final int tier;
    private final String description;
    private final boolean isUpgraded;

    @Override
    public String getName() {
        return "";
    }

    @Override
    public int getAttack() {
        return 0;
    }

    @Override
    public int getArmor() {
        return 0;
    }

    @Override
    public int getMaxHp() {
        return 0;
    }

    @Override
    public int getMoveRange() {
        return 0;
    }

    @Override
    public Range<Integer> getDamage() {
        return null;
    }

    @Override
    public int getTier() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public boolean isUpgraded() {
        return false;
    }
}
