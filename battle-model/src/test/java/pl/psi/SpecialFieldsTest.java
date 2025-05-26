package pl.psi;

import java.util.HashMap;
import java.util.List;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Range;
import org.junit.jupiter.api.Disabled;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
public class SpecialFieldsTest {

    private static final Range<Integer> NOT_IMPORTANT_DMG = Range.closed(0, 0);

    @Test
    void isFieldGivingDmg() {
        final Creature creature = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .attack(0)
                        .moveRange(5)
                        .armor(10)
                        .build())
                .build();

        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .attack(0)
                        .moveRange(5)
                        .armor(10)
                        .build())
                .build();

        final List< Creature > c1 = List.of( creature, dragon );
        final List< Creature > c2 = List.of();
        final BiMap <Point, SpecialField > specialFields = HashBiMap.create();
        specialFields.put(new Point(3, 3), new DmgField());
        specialFields.put(new Point(4, 4), new DmgField());
        final Board board = new Board( c1, c2,  specialFields, new HashMap<>());

        //when
        board.move( creature, new Point( 3, 3 ) );
        board.move( dragon, new Point(4, 4));

        //then
        assertThat(creature.getCurrentHp()).isEqualTo(80);
        assertThat(dragon.getCurrentHp()).isEqualTo(80);
    }

//    @Test
//    void damageBuff(){
//        final Creature creature1 = new Creature.Builder().statistic(CreatureStats.builder()
//                        .maxHp(100)
//                        .damage(Range.closed(10, 10))
//                        .attack(50)
//                        .armor(0)
//                        .build())
//                .build();
//
//        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
//                        .maxHp(100)
//                        .damage(Range.closed(10, 10))
//                        .attack(50)
//                        .armor(0)
//                        .build())
//                .build();
//
//        final List< Creature > c1 = List.of( creature1, dragon);
//        final List< Creature > c2 = List.of();
//        final Board board = new Board(c1, c2);
//
//        board.attackBuffedField(new Point(3, 3), 10);
//        board.attackBuffedFIeld(new Point(4, 4), 20);
//
//        //When
//        board.move(creature1, new Point(3, 3));
//        board.move(dragon, new Point(4, 4));
//
//
//        //Then
//        assertThat(creature1.setDamage().isEqual(60));
//        assertThat(dragon.setDamage.isEqual(70));
//    }
}

