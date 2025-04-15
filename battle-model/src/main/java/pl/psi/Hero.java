package pl.psi;

import java.util.List;

import pl.psi.creatures.Creature;

import lombok.Getter;
import lombok.Setter;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Hero
{
    @Getter
    private final List< Creature > creatures;
    
    @Getter
    private int moral;
    
    @Getter
    private int luck;

    public Hero(final List< Creature > creatures)
    {
        this.creatures = creatures;
    }

    public Hero(final List< Creature > creatures, final int moral, final int luck )
    {
        this.creatures = creatures;
        if (moral < -3 || moral > 3) {
            throw new IllegalArgumentException("Moral must be between -3 and 3");
        }
        this.moral = moral;
        
        if (luck < 0 || luck > 3) {
            throw new IllegalArgumentException("Luck must be between 0 and 3");
        }
        this.luck = luck;
    }

    public int getMoral() {
        return moral;
    }

    public int getLuck() {
        return luck;
    }
}
