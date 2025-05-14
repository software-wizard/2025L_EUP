package pl.psi;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import pl.psi.creatures.Creature;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Board
{
    private static final int MAX_WITDH = 14;
    private final BiMap< Point, Creature > map = HashBiMap.create();
    private final BiMap< Point, String > mapWithSpecialFields = HashBiMap.create();

    public Board( final List< Creature > aCreatures1, final List< Creature > aCreatures2)
    {
        addCreatures( aCreatures1, 0 );
        addCreatures( aCreatures2, MAX_WITDH );
    }

    public Board( final List< Creature > aCreatures1, final List< Creature > aCreatures2, BiMap< Point, String > aSpecialFields )
    {
        this(aCreatures1, aCreatures2);
        addSpecialFields( aSpecialFields );
    }

    private void addCreatures( final List< Creature > aCreatures, final int aXPosition )
    {
        for( int i = 0; i < aCreatures.size(); i++ )
        {
            map.put( new Point( aXPosition, i * 2 + 1 ), aCreatures.get( i ) );
        }
    }

    private void addSpecialFields( final BiMap <Point, String> aSpecialFields)
    {
        for( int i = 0; i < aSpecialFields.size(); i++ )
        {
            mapWithSpecialFields.put( aSpecialFields.inverse().get( i ), aSpecialFields.get( i ) );
        }
    }

    Optional< Creature > getCreature( final Point aPoint )
    {
        return Optional.ofNullable( map.get( aPoint ) );
    }

    void move( final Creature aCreature, final Point aPoint )
    {
        if( canMove( aCreature, aPoint ) )
        {
            if (mapWithSpecialFields.containsKey(aPoint)) {
                String typeOfField = mapWithSpecialFields.get(aPoint).toString();
                SpecialField.doSomething(typeOfField);
            }
            map.inverse()
                .remove( aCreature );
            map.put( aPoint, aCreature );
        }
    }

    boolean canMove( final Creature aCreature, final Point aPoint )
    {
        if( map.containsKey( aPoint ) )
        {
            return false;
        }
        final Point oldPosition = getPosition( aCreature );
        return aPoint.distance( oldPosition.getX(), oldPosition.getY() ) < aCreature.getMoveRange();
    }

    Point getPosition( Creature aCreature )
    {
        return map.inverse()
            .get( aCreature );
    }
}
