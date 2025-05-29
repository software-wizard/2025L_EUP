package pl.psi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

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

        FireWallSpell wall = new FireWallSpell("", 1, new Point(1,2), 2, testBoard);

        testBoard.move(creature, new Point(3,3));

        assertThat(creature.getCurrentHp()).isEqualTo(70);
    }

    @Test
    void DoesExaminePathWork(){
        List<Point> correctPath = List.of(
                new Point(1, 2),
                new Point(2, 3),
                new Point(3,3)
        );

        List<Point> examinedPath = examinePath(new Point(0,1), new Point(3,3));


        assertEquals(correctPath, examinedPath);
    }

    private List<Point> examinePath(Point start, Point end) {

        List<Point> path = new ArrayList<>();

        //zmienne określające kierunek w zależności od pozycji
        int dx = Integer.signum(end.getX() - start.getX());
        int dy = Integer.signum(end.getY() - start.getY());


        //współrzędne startowe
        int x = start.getX();
        int y = start.getY();


        while (x != end.getX() || y != end.getY()) {
            if (x != end.getX()) x += dx;
            if (y != end.getY()) y += dy;
            path.add(new Point(x, y));
        }

        return path;
    }

}
