package pl.psi.hero;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import pl.psi.Hero;
import pl.psi.creatures.EconomyCreature;
import pl.psi.map.resources.Resources;

public class EconomyHero implements PropertyChangeListener
{

    //TODO przepisać get gold i add gold na resource

    private final Fraction fraction;
    private final List< EconomyCreature > creatureList;
    @Getter
    private Resources resources;
    @Getter
    private final int moveRange = 10;
    private int remainingMoves;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public EconomyHero( final Fraction aFraction, final Resources aResources)
    {
        fraction = aFraction;
        creatureList = new ArrayList<>();
        remainingMoves = moveRange;
        resources = aResources;
    }

    public void resetMoveRange() {
        this.remainingMoves = moveRange;
    }

    public boolean canMoveTo(double distance) {
        return distance <= remainingMoves;
    }

    public void deductMove(double distance) {
        remainingMoves -= distance;
    }

    public int getRemainingMoveRange() {
        return remainingMoves;
    }

    public void addCreature(final EconomyCreature aCreature)
    {
        if( creatureList.size() >= 7 )
        {
            throw new IllegalStateException( "Hero has not empty slot for creature" );
        }
        creatureList.add( aCreature );
    }

    public void addResource(final Resources changedResources) {
        Resources oldResources = this.resources;
        this.resources = this.resources.change(changedResources);
        pcs.firePropertyChange("resources", oldResources, this.resources);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public boolean canAfford(Resources cost) {
        return resources.enoughToPay(cost);
    }

    public void pay(Resources cost) {
        if (!canAfford(cost)) {
            throw new IllegalStateException("Not enough resources");
        }
        this.resources = this.resources.change(cost.pay()); // pay() returns the negative values
    }

    public List< EconomyCreature > getCreatures()
    {
        return List.copyOf( creatureList );
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

    public enum Fraction
    {
        NECROPOLIS;
    }



}
