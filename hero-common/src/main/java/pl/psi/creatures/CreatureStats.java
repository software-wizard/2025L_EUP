package pl.psi.creatures;

import com.google.common.collect.Range;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
@Builder

public class CreatureStats implements CreatureStatisticIf{
    private final String name;
    @Setter
    private  int attack;

    private final int armor;
    private final int maxHp;
    private final int moveRange;
    private final Range< Integer > damage;
    private final int tier;
    private final String description;
    private final boolean isUpgraded;
}
