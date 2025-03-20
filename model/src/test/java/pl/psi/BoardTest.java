package pl.psi;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {

    @Test
    void shouldAddCreaturesToProperlySlots() {
        Creature c1 = new Creature(10, 42, 1000, 5, Range.closed(5, 5),1);
        Creature c2 = new Creature(10, 42, 1000, 5, Range.closed(5, 5),5);
        Creature c3 = new Creature(10, 42, 1000, 5, Range.closed(5, 5),5);
        Board board = new Board(
                List.of(c1),
                List.of(c2,c3));

        assertThat(board.get(new Point(0,1))).isEqualTo(c1);
        assertThat(board.get(new Point(14,1))).isEqualTo(c2);
        assertThat(board.get(new Point(14,3))).isEqualTo(c3);
    }

    @Test
    void shouldMoveCreature() {
        Creature c1 = new Creature(10, 42, 1000, 5, Range.closed(5, 5),5);
        Board board = new Board(
                List.of(c1),
                List.of());

        board.move(c1, new Point(2,2));

        assertThat(board.get(new Point(0,1))).isEqualTo(null);
        assertThat(board.get(new Point(2,2))).isEqualTo(c1);
    }

    @Test
    void shouldNotMoveWhenMoveRangeIsNotEnought() {
        Creature c1 = new Creature(10, 42, 1000, 5, Range.closed(5, 5),5);
        Board board = new Board(
                List.of(c1),
                List.of());

        assertThatThrownBy( () ->board.move(c1, new Point(12,2)));

        assertThat(board.get(new Point(0,1))).isEqualTo(c1);
    }

}