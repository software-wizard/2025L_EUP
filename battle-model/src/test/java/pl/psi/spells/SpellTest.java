package pl.psi.spells;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.psi.Hero;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SpellTest {
    @Test
    void damagingSpellShouldApplyDamage(){
        // given
        final Creature c1 = new Creature.Builder()
                .statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(5, 10))
                        .attack(10)
                        .armor(5)
                        .build())
                .build();

        final Hero h1 = new Hero(List.of(c1));


        h1.applyDamageSpell(c1);

        assertThat(c1.getCurrentHp()).isEqualTo(80);
    }



}
