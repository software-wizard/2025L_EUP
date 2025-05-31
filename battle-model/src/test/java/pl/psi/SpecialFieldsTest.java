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
        final BiMap <BattlePoint, SpecialField > specialFields = HashBiMap.create();
        specialFields.put(new BattlePoint(3, 3), new DmgField());
        specialFields.put(new BattlePoint(4, 4), new DmgField());
        final Board board = new Board( c1, c2,  specialFields, new HashMap<>());

        //when
        board.move( creature, new BattlePoint( 3, 3 ) );
        board.move( dragon, new BattlePoint(4, 4));

        //then
        assertThat(creature.getCurrentHp()).isEqualTo(80);
        assertThat(dragon.getCurrentHp()).isEqualTo(80);
    }

//    @Test
//    void fieldCanOnlyBeFlown() {
//        final Creature creature = new Creature.Builder().statistic(CreatureStats.builder()
//                        .maxHp(100)
//                        .damage(NOT_IMPORTANT_DMG)
//                        .attack(0)
//                        .name("Knight")
//                        .moveRange(5)
//                        .armor(10)
//                        .build())
//                .build();
//
//        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
//                        .maxHp(100)
//                        .name("Ghost Dragon")
//                        .damage(NOT_IMPORTANT_DMG)
//                        .attack(0)
//                        .moveRange(5)
//                        .armor(10)
//                        .build())
//                .build();
//
//        final List< Creature > c1 = List.of( creature, dragon );
//        final List< Creature > c2 = List.of();
//        final BiMap <BattlePoint, SpecialField > specialFields = HashBiMap.create();
//        specialFields.put(new BattlePoint(3, 3), new FieldCanOnlyBeFlown());
//        final Board board = new Board( c1, c2,  specialFields, new HashMap<>());
//        final BattlePoint initialCreaturePoint = creature.getCurrentCreaturePoint();
//
//        //when
//        board.move( creature, new BattlePoint( 3, 3 ) );
//        board.move( dragon, new BattlePoint(3, 3));
//
//        //then
//        assertThat(creature.getCurrentCreaturePoint()).isEqualTo(initialCreaturePoint);
//        assertThat(dragon.getCurrentCreaturePoint()).isEqualTo(new BattlePoint(3,3));
//    }

    @Test
    void buffFieldTest(){
        final Creature creature1 = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .moveRange(5)
                        .damage(Range.closed(10, 10))
                        .attack(50)
                        .armor(0)
                        .build())
                .build();

        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .moveRange(5)
                        .damage(Range.closed(10, 10))
                        .attack(50)
                        .armor(0)
                        .build())
                .build();

        final List< Creature > c1 = List.of( creature1, dragon);
        final List< Creature > c2 = List.of();
        final BiMap <BattlePoint, SpecialField > specialFields = HashBiMap.create();
        specialFields.put(new BattlePoint(3, 3), new BuffField());
        final Board board = new Board( c1, c2,  specialFields, new HashMap<>());

        CreatureStats oldCreature1Stats = (CreatureStats) creature1.getStats();
        CreatureStats oldDragonStats = (CreatureStats) creature1.getStats();

        //When
        board.move(creature1, new BattlePoint(3, 3));
        board.move(dragon, new BattlePoint(4, 4));


        //Then
        assertThat(creature1.getAttack()).isEqualTo(oldCreature1Stats.getAttack() + 5);
        assertThat(creature1.getArmor()).isEqualTo(oldCreature1Stats.getArmor() + 10);
        assertThat(creature1.getMaxHp()).isEqualTo(oldCreature1Stats.getMaxHp() + 20);
        assertThat(creature1.getMoveRange()).isEqualTo(oldCreature1Stats.getMoveRange() + 1);
        assertThat(dragon.getAttack()).isEqualTo(oldDragonStats.getAttack());
        assertThat(dragon.getArmor()).isEqualTo(oldDragonStats.getArmor());
        assertThat(dragon.getMaxHp()).isEqualTo(oldDragonStats.getMaxHp());
        assertThat(dragon.getMoveRange()).isEqualTo(oldDragonStats.getMoveRange());
    }

    @Test
    void debuffFieldTest(){
        final Creature creature1 = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .moveRange(5)
                        .damage(Range.closed(10, 10))
                        .attack(50)
                        .armor(0)
                        .build())
                .build();

        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .moveRange(5)
                        .damage(Range.closed(10, 10))
                        .attack(50)
                        .armor(0)
                        .build())
                .build();

        final List< Creature > c1 = List.of( creature1, dragon);
        final List< Creature > c2 = List.of();
        final BiMap <BattlePoint, SpecialField > specialFields = HashBiMap.create();
        specialFields.put(new BattlePoint(3, 3), new DebuffField());
        final Board board = new Board( c1, c2,  specialFields, new HashMap<>());

        CreatureStats oldCreature1Stats = (CreatureStats) creature1.getStats();
        CreatureStats oldDragonStats = (CreatureStats) creature1.getStats();

        //When
        board.move(creature1, new BattlePoint(3, 3));
        board.move(dragon, new BattlePoint(4, 4));


        //Then
        assertThat(creature1.getAttack()).isEqualTo(oldCreature1Stats.getAttack() - 5);
        assertThat(creature1.getArmor()).isEqualTo(oldCreature1Stats.getArmor() - 10);
        assertThat(creature1.getMaxHp()).isEqualTo(oldCreature1Stats.getMaxHp() - 20);
        assertThat(creature1.getMoveRange()).isEqualTo(oldCreature1Stats.getMoveRange() - 1);
        assertThat(dragon.getAttack()).isEqualTo(oldDragonStats.getAttack());
        assertThat(dragon.getArmor()).isEqualTo(oldDragonStats.getArmor());
        assertThat(dragon.getMaxHp()).isEqualTo(oldDragonStats.getMaxHp());
        assertThat(dragon.getMoveRange()).isEqualTo(oldDragonStats.getMoveRange());
    }
}

