package pl.psi.hero;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import pl.psi.Hero;
import pl.psi.creatures.EconomyCreature;

public class EconomyHero implements PropertyChangeListener
{

    private final Fraction fraction;
    private final List< EconomyCreature > creatureList;
    private int gold;
    private final int moveRange;

    public EconomyHero( final Fraction aFraction, final int aGold )
    {
        fraction = aFraction;
        gold = aGold;
        creatureList = new ArrayList<>();
        moveRange = 5;
    }

    public void addCreature(final EconomyCreature aCreature)
    {
        if( creatureList.size() >= 7 )
        {
            throw new IllegalStateException( "Hero has not empty slot for creature" );
        }
        creatureList.add( aCreature );
    }

    public int getGold()
    {
        return gold;
    }

    public int getMoveRange() {
        return moveRange;
    }

    public void addGold(final int aAmount) {
        gold += aAmount;
    }

    public List< EconomyCreature > getCreatures()
    {
        return List.copyOf( creatureList );
    }

    void substractGold( final int aAmount )
    {
        if( aAmount > gold )
        {
            throw new IllegalStateException( "Hero has not enought money" );
        }
        gold -= aAmount;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public enum Fraction
    {
        NECROPOLIS;
    }
}
