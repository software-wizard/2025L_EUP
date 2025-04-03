package pl.psi;

import java.util.List;

import com.google.common.collect.Range;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpecialFieldsTest {

    private static final Range<Integer> NOT_IMPORTANT_DMG = Range.closed(0, 0);

    @Test
    void isFieldGivingDmg() {
        final Creature creature = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .attack(0)
                        .armor(10)
                        .build())
                .build();

        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .attack(0)
                        .armor(10)
                        .build())
                .build();

        final List< Creature > c1 = List.of( creature, dragon );
        final List< Creature > c2 = List.of();
        final Board board = new Board( c1, c2 );

        board.setFieldGivingDmg(new Point(3, 3), 20); //(point, howMuchDmg)
        board.setFieldGivingDmg(new Point(4, 4), 15);

        //when
        board.move( creature, new Point( 3, 3 ) );
        board.move( dragon, new Point(4, 4));

        //then
        assertThat(creature.getCurrentHp()).isEqual(80);
        assertThat(dragon.getCurrentHp()).isEqual(100);
    }
}
