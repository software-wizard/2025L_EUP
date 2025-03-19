package pl.psi;

import java.util.List;

public class GameEngine {
    private final TurnQueue queue;

    GameEngine(List<Creature> aC1, List<Creature> aC2) {
        queue = new TurnQueue(aC1, aC2);
    }


    void pass() {
        queue.pass();
    }

    void attack(Creature aDefender) {
        queue.getActiveCreature().attack(aDefender);
        queue.pass();
    }
}
