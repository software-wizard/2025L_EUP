package pl.psi;

import java.util.List;

public class GameEngine {
    private final TurnQueue queue;
    private final Board board;

    GameEngine(List<Creature> aC1, List<Creature> aC2) {
        queue = new TurnQueue(aC1, aC2);
        board = new Board(aC1, aC2);
    }


    void pass() {
        queue.pass();
    }

    void attack(Creature aDefender) {
        queue.getActiveCreature().attack(aDefender);
        queue.pass();
    }

    Creature getCreature(Point aPoint) {
        return board.get(aPoint);
    }

    boolean canMove(Point aPoint) {
        Creature creature = queue.getActiveCreature();
        return board.canMove(creature, aPoint, board.getPosition(creature));
    }

    boolean getActiveCreature(Point aPoint) {
        return (queue.getActiveCreature()).equals(board.get(aPoint));
    }
}
