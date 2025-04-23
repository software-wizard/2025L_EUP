package pl.psi;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

class BoardTest
{
    @Test
    void UnitsTeleportProperly()
    {
        final Creature creature = new Creature.Builder().statistic( CreatureStats.builder()
                        .moveRange( 5 )
                        .build() )
                .build();
        final List< Creature > c1 = List.of( creature );
        final List< Creature > c2 = List.of();
        final Board board = new Board( c1, c2 );

        board.move( creature, new Point( 3, 3 ) );

        assertThat( board.getCreature( new Point( 3, 3 ) )
                .isPresent() ).isTrue();
    }


    void flyingUnitsMoveProperly()
    {
        Creature flyer = new Creature.Builder()
                .statistic(CreatureStats.builder()
                        .moveRange(5)
                        .movementType(MovementType.FLYING)
                        .build())
                .build();

        Board board = new Board(List.of(flyer), List.of());
        board.addObstacle(new Point(0, 1)); // przeszkoda nie przeszkadza lataniu

        board.move(flyer, new Point(0, 3));

        assertThat(board.getLastPath()).isEqualTo(List.of(
                new Point(0, 1),
                new Point(0, 2),
                new Point(0, 3)
        ));
    }


    void UnitsMoveProperly()
    {
        final Creature walker = new Creature.Builder()
                .statistic(CreatureStats.builder()
                        .moveRange(5)
                        .movementType(Walking)
                        .build())
                .build();

        Board board = new Board(List.of(walker), List.of());
        board.addObstacle(new Point(0, 1)); // przeszkoda prosto na drodze

        board.move(walker, new Point(0, 3));

        assertThat(board.getLastPath()).isEqualTo(List.of(
                new Point(1, 0),
                new Point(1, 1),
                new Point(1, 2),
                new Point(1, 3),
                new Point(0, 3)
        ));

    }