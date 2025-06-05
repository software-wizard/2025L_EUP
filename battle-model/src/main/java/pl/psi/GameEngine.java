package pl.psi;

import com.google.common.collect.BiMap;
import pl.psi.Spells.Spell;
import pl.psi.creatures.Creature;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;
import java.util.Optional;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class GameEngine {

    public static final String CREATURE_MOVED = "CREATURE_MOVED";
    private static final String SPELL_CAST = "SPELL_CAST";
    private final TurnQueue turnQueue;
    private Board board;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
    private final Hero hero1;
    private final Hero hero2;

    public GameEngine(final Hero aHero1, final Hero aHero2) {
        this.hero1 = aHero1;
        this.hero2 = aHero2;
        turnQueue = new TurnQueue(aHero1.getCreatures(), aHero2.getCreatures());
        board = new Board(aHero1.getCreatures(), aHero2.getCreatures());
    }

    public GameEngine(final Hero aHero1, final Hero aHero2, final BiMap<BattlePoint, SpecialField> specialFields, Map<BattlePoint, Creature> aBankEnemy) {
        this(aHero1, aHero2);
        board = new Board(aHero1.getCreatures(), aHero2.getCreatures(), specialFields, aBankEnemy);
    }

    public void attack(final BattlePoint aBattlePoint) {
        board.getCreature(aBattlePoint)
                .ifPresent(defender -> {
                    Creature attacker = turnQueue.getCurrentCreature();
                    attacker.attack(defender);

                    if (!defender.isAlive()) {
                        board.removeCreature(defender);
                        removeDeadCreature(defender);
                    }

                    if (!attacker.isAlive()) {
                        board.removeCreature(attacker);
                        removeDeadCreature(attacker);
                    }
                });

        pass();
    }

    private void removeDeadCreature(Creature creature) {
        Hero owner = getOwnerOf(creature);
        owner.removeCreature(creature);
    }

    private Hero getOwnerOf(Creature creature) {
        if (hero1.getCreatures().contains(creature)) {
            return hero1;
        } else if (hero2.getCreatures().contains(creature)) {
            return hero2;
        } else {
            throw new IllegalStateException("Creature not owned by any hero");
        }
    }


    public boolean canMove(final BattlePoint aBattlePoint) {
        return board.canMove(turnQueue.getCurrentCreature(), aBattlePoint);
    }

    public void move(final BattlePoint aBattlePoint) {
        board.move(turnQueue.getCurrentCreature(), aBattlePoint);
        observerSupport.firePropertyChange(CREATURE_MOVED, null, aBattlePoint);
    }

    public Optional<Creature> getCreature(final BattlePoint aBattlePoint) {
        return board.getCreature(aBattlePoint);
    }

    public void pass() {
        turnQueue.next();
    }

    public void addObserver(final PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aObserver);
        turnQueue.addObserver(aObserver);
    }

    public boolean canAttack(final BattlePoint aBattlePoint) {
        double distance = board.getPosition(turnQueue.getCurrentCreature())
                .distance(aBattlePoint);
        return board.getCreature(aBattlePoint)
                .isPresent()
                && distance < 2 && distance > 0;
    }

    public boolean isCurrentCreature(BattlePoint aBattlePoint) {
        return Optional.of(turnQueue.getCurrentCreature()).equals(board.getCreature(aBattlePoint));
    }

    public BiMap<BattlePoint, SpecialField> getSpecialFields() {
        return board.getSpecialFields();
    }

    public void interact(BattlePoint aCurrentBattlePoint) {
        board.interact(turnQueue.getCurrentCreature(), aCurrentBattlePoint);
    }


    public Hero getCurrentHero() {
        Creature current = turnQueue.getCurrentCreature();
        if (hero1.getCreatures().contains(current)) {
            return hero1;
        } else {
            return hero2;
        }
    }

    public void castSpell(Spell spell, Creature targetCreature) {
        getCurrentHero().apply(spell, targetCreature);
        notifySpellCast(spell);
    }

    private void notifySpellCast(Spell spell) {
        observerSupport.firePropertyChange(SPELL_CAST, null, spell);
    }

    public static class BuffField {
    }
}
