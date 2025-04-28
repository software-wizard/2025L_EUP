package pl.psi.creatures;

import pl.psi.Hero;

import java.beans.PropertyChangeEvent;
import java.util.Random;

public class LuckyCreature extends Creature
{
    private final Creature decorated;
    private final Hero hero;
    private final Random random;

    public LuckyCreature(final Creature aDecorated, final Hero aHero) {
        decorated = aDecorated;
        hero = aHero;
        random = new Random();
    }

    public boolean shouldDoubleDamage()
    {
        int luck = hero.getLuck();

        if (luck == 0) return false;

        double chance = 0;
        switch (luck) {
            case 1: chance = 0.083; break;
            case 2: chance = 0.167; break;
            case 3: chance = 0.25; break;
        }
        return random.nextDouble() < chance;
    }

    @Override
    public CreatureStatisticIf getStats() {
        return decorated.getStats();
    }

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
    public void setCurrentHp(int aCurrentHp) {
        decorated.setCurrentHp(aCurrentHp);
    }

    @Override
    public void attack(final Creature aDefender) {
        if (shouldDoubleDamage()) {
            decorated.attack(aDefender);
            decorated.attack(aDefender);
        } else {
            decorated.attack(aDefender);
        }
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
    public String getName() {
        return decorated.getName();
    }

    @Override
    public int getMoveRange() {
        return decorated.getMoveRange();
    }
}
