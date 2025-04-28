package pl.psi.hero;

import java.util.ArrayList;
import java.util.List;

import pl.psi.creatures.EconomyCreature;

public class EconomyHero
{

    private final Fraction fraction;
    private final List< EconomyCreature > creatureList;
    private int gold;
    private int moral;
    private int luck;

    public EconomyHero( final Fraction aFraction, final int aGold )
    {
        fraction = aFraction;
        gold = aGold;
        creatureList = new ArrayList<>();
        moral = 0;
        luck = 0;
    }

    public EconomyHero( final Fraction aFraction, final int aGold, final int aMoral, final int aLuck )
    {
        fraction = aFraction;
        gold = aGold;
        creatureList = new ArrayList<>();
        if (aMoral < -3 || aMoral > 3) {
            throw new IllegalArgumentException("Moral must be between -3 and 3");
        }
        this.moral = aMoral;

        if (aLuck < 0 || aLuck > 3) {
            throw new IllegalArgumentException("Luck must be between 0 and 3");
        }
        this.luck = aLuck;
    }

    void addCreature( final EconomyCreature aCreature )
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

    public void addGold( final int aAmount )
    {
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

    public int getMoral() {
        return moral;
    }

    public int getLuck() {
        return luck;
    }

    public enum Fraction
    {
        NECROPOLIS;
    }
}
