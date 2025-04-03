package pl.psi.spells;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

public class SpellTest {
    @Test
    void damagingSpellShouldApplyDamage(){
        // given
        final Creature c1 = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .attack(50)
                        .armor(0)
                        .build())
                .build();

        Hero.applyDamageSpell(c1);

        aassertThat(c1.getCurrentHp()).isEqualTo(70);
    }

}
