package pl.psi.creatures;

import java.beans.PropertyChangeEvent;
import java.util.Random;

import pl.psi.Hero;

public class MoraleCreature extends Creature {
    private final Creature decorated;
    private final Hero hero;
    private final Random random;

    public MoraleCreature(final Creature aDecorated, final Hero aHero) {
        decorated = aDecorated;
        hero = aHero;
        random = new Random();
    }

    public boolean shouldGetExtraTurn() {
        int morale = hero.getMoral();
        if (morale <= 0) return false;

        double chance = 0;
        switch (morale) {
            case 1: chance = 0.042; break;
            case 2: chance = 0.083; break;
            case 3: chance = 0.125; break;
        }
        return random.nextDouble() < chance;
    }

    public boolean shouldSkipTurn() {
        int morale = hero.getMoral();
        if (morale >= 0) return false;
        
        double chance = 0;
        switch (morale) {
            case -1: chance = 0.083; break;
            case -2: chance = 0.167; break;
            case -3: chance = 0.25; break;
        }
        return random.nextDouble() < chance;
    }

    @Override
    public CreatureStatisticIf getStats() {
        return decorated.getStats();
    }

    @Override
    public String getName() {return decorated.getName();}

    @Override
    public int getAmount() {
        return decorated.getAmount();
    }

    @Override
    public void setAmount(int amount) {
        decorated.setAmount(amount);
    }

    @Override
    public int getCurrentHp() {
        return decorated.getCurrentHp();
    }

    @Override
    public void attack(final Creature aDefender) {
        decorated.attack(aDefender);
    }

    @Override
    public boolean isAlive() {
        return decorated.isAlive();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        decorated.propertyChange(evt);
    }

    @Override
    public int getMoveRange() {
        return decorated.getMoveRange();
    }
} 