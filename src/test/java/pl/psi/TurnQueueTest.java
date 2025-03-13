package pl.psi;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TurnQueueTest {

    @Test
    void x() {
        Creature c1 = mock(Creature.class);
        when(c1.getMoveRange()).thenReturn(5);
        Creature c2 = mock(Creature.class);
        when(c2.getMoveRange()).thenReturn(10);
        Creature c3 = mock(Creature.class);
        when(c3.getMoveRange()).thenReturn(3);
        TurnQueue queue = new TurnQueue(List.of(c1), List.of(c2, c3));


        assertThat(queue.getActiveCreature()).isEqualTo(c2);
        assertThat(queue.getActiveCreature()).isEqualTo(c2);

        queue.pass();
        assertThat(queue.getActiveCreature()).isEqualTo(c1);

        queue.pass();
        assertThat(queue.getActiveCreature()).isEqualTo(c3);

        queue.pass();
        assertThat(queue.getActiveCreature()).isEqualTo(c2);

        assertThat(queue.getActiveCreature()).isEqualTo(c2);
        assertThat(queue.getActiveCreature()).isEqualTo(c2);

        queue.pass();
        assertThat(queue.getActiveCreature()).isEqualTo(c1);

        queue.pass();
        assertThat(queue.getActiveCreature()).isEqualTo(c3);
    }

}