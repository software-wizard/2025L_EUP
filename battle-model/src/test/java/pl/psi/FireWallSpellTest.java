package pl.psi;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.jupiter.api.Test;

import pl.psi.Spells.FireWallSpell;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;




class FireWallSpellTest {

    @Test
    void FireWallShouldDamageCreatures(){
        final Creature creature = new Creature.Builder().statistic( CreatureStats.builder()
                        .moveRange( 5 )
                        .maxHp(100)
                        .armor(0)
                        .build() )
                .build();
        final List< Creature > c1 = List.of( creature );
        final List< Creature > c2 = List.of();


        //rozmieszcza jednostki na pozycji 0,1 i 14,14
        final Board testBoard = new Board( c1, c2 );
        BiMap< Point, SpecialField > mapWithSpecialFields = HashBiMap.create();


        FireWallSpell wall = new FireWallSpell("", 1, new Point(1,1), 2);

        testBoard.move(creature, new Point(0,2));

        assertThat(testBoard.getPosition(creature)).isEqualTo(new Point(0,2));
    }


}
