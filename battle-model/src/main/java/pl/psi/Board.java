package pl.psi;

import java.util.*;
import java.util.Optional;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import pl.psi.creatures.Creature;
//import pl.psi.creatures.MovementType;

public class Board
{
    private static final int MAX_WIDTH = 14;
    private final BiMap< Point, Creature > map = HashBiMap.create();
    private final Set< Point > obstacles = new HashSet<>();
    private List< Point > lastPath = List.of();

    public Board( final List< Creature > aCreatures1, final List< Creature > aCreatures2 )
    {
        addCreatures( aCreatures1, 0 );
        addCreatures( aCreatures2, MAX_WIDTH );
    }

    private void addCreatures( final List< Creature > aCreatures, final int aXPosition )
    {
        for( int i = 0; i < aCreatures.size(); i++ )
        {
            map.put( new Point( aXPosition, i * 2 + 1 ), aCreatures.get( i ) );
        }
    }

    Optional< Creature > getCreature( final Point aPoint )
    {
        return Optional.ofNullable( map.get( aPoint ) );
    }

    void move( final Creature aCreature, final Point aTarget )
    {
        final List<Point> path = findPath(aCreature, aTarget);
        if (!path.isEmpty())
        {
            map.inverse().remove(aCreature);
            map.put(aTarget, aCreature);
            lastPath = path;
        }
        else
        {
            lastPath = List.of();
        }
    }

    boolean canMove( final Creature aCreature, final Point aPoint )
    {
        return !findPath(aCreature, aPoint).isEmpty();
    }

    Point getPosition( Creature aCreature )
    {
        return map.inverse().get( aCreature );
    }

    void addObstacle( Point point )
    {
        obstacles.add( point );
    }

    public List<Point> getLastPath()
    {
        return lastPath;
    }

    private boolean isInBounds( Point p )
    {
        return p.x >= 0 && p.y >= 0 && p.x <= MAX_WIDTH && p.y <= MAX_WIDTH;
    }

    private List<Point> neighbors( Point p )
    {
        return List.of(
                new Point( p.x + 1, p.y ),
                new Point( p.x - 1, p.y ),
                new Point( p.x, p.y + 1 ),
                new Point( p.x, p.y - 1 )
        );
    }

    private List<Point> findPath( Creature creature, Point goal )
    {
        final Point start = getPosition( creature );
        //final boolean canFly = creature.getStats().getMovementType() == MovementType.FLYING;
        final int range = creature.getMoveRange();

        Queue<List<Point>> queue = new LinkedList<>();
        Set<Point> visited = new HashSet<>();

        queue.add( List.of( start ) );
        visited.add( start );

        while( !queue.isEmpty() )
        {
            final List<Point> path = queue.poll();
            final Point current = path.get( path.size() - 1 );

            if( current.equals( goal ) )
            {
                return path.subList( 1, path.size() );
            }

            for( Point neighbor : neighbors( current ) )
            {
                if( !isInBounds( neighbor ) ) continue;
                if( visited.contains( neighbor ) ) continue;
                if( map.containsKey( neighbor ) ) continue;
                //if( !canFly && obstacles.contains( neighbor ) ) continue;

                List<Point> newPath = new ArrayList<>( path );
                newPath.add( neighbor );

                if( newPath.size() - 1 > range ) continue;

                queue.add( newPath );
                visited.add( neighbor );
            }
        }

        return List.of(); // no path found
    }
}
