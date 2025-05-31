package pl.psi.creatures;

import com.google.common.collect.Range;

import lombok.*;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@RequiredArgsConstructor
@Builder
@Getter
public class CreatureStats implements CopyableStatisticIf{
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
    public CopyableStatisticIf copy() {
        return CreatureStats.builder()
                .name(name)
                .attack(attack)
                .armor(armor)
                .maxHp(maxHp)
                .moveRange(moveRange)
                .damage(Range.closed(damage.lowerEndpoint(), damage.upperEndpoint()))
                .tier(tier)
                .description(description)
                .isUpgraded(isUpgraded)
                .build();
    }
}
