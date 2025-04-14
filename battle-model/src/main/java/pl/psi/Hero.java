package pl.psi;

import java.util.List;

import pl.psi.creatures.Creature;

import lombok.Getter;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Hero
{
    @Getter
    private final List< Creature > creatures;

    public Hero( final List< Creature > aCreatures )
    {
        creatures = aCreatures;
    }

    public void applyDamageSpell(Creature c1) {
            final double hpToSubstract =  0.2* c1.getMaxHp();
            int hp = (int) (c1.getCurrentHp() - hpToSubstract);
            c1.setCurrentHp(hp);
        }


}
