package pl.psi;

import java.util.ArrayList;
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
    private final BiMap< Point, SpecialField > mapWithSpecialFields = HashBiMap.create();

    public Board( final List< Creature > aCreatures1, final List< Creature > aCreatures2)
    {
        addCreatures( aCreatures1, 0 );
        addCreatures( aCreatures2, MAX_WITDH );
    }

    public Board( final List< Creature > aCreatures1, final List< Creature > aCreatures2, List< SpecialField > aSpecialFields )
    {
        this(aCreatures1, aCreatures2);
        addSpecialFields( aSpecialFields );
    }

    public void addCreatures( final List< Creature > aCreatures, final int aXPosition )
    {
        for( int i = 0; i < aCreatures.size(); i++ )
        {
            map.put( new Point( aXPosition, i * 2 + 1 ), aCreatures.get( i ) );
        }
    }

    private void addSpecialFields( final List<SpecialField> aSpecialFields)
    {
        for( int i = 0; i < aSpecialFields.size(); i++ )
        {
            mapWithSpecialFields.put( new Point( (int) Math.round(Math.random() * 14), (int) Math.round(Math.random() * 14)), aSpecialFields.get( i ) );
        }
    }

    //Utworzyłem te metodę, aby móc dodawać nowe pola specjalne do istniejącej planszy np. za pomocą zaklęć
    public void addSpecialFieldOpen(final BiMap<Point, SpecialField> aSpecialFields){
        for (Point point : aSpecialFields.keySet()) {
            mapWithSpecialFields.put(point, aSpecialFields.get(point));
        }
    }

    public Optional< Creature > getCreature(final Point aPoint)
    {
        return Optional.ofNullable( map.get( aPoint ) );
    }

    void move( final Creature aCreature, final Point aPoint ) {

        if( canMove( aCreature, aPoint ) ){
            List<Point> path = examinePath(getPosition(aCreature), aPoint);

            for (Point point:path){
                if (mapWithSpecialFields.containsKey(point)){
                    SpecialField currentField = mapWithSpecialFields.get(point);

                    //Ten warunek sprawdza, czy pole specjalne na ściezce ruchu powinno aktywowac sie po przejsciu jednostki
                    if (currentField.getTypeOfField().equals(("TRIGGERED BY STEPPING"))){
                        currentField.doSomething(aCreature);
                    }
                }
            }

            if (!aCreature.isAlive()){
                return;
            }
        }


        if( canMove( aCreature, aPoint ) )
        {
            if (mapWithSpecialFields.containsKey(aPoint)) {
                SpecialField field = mapWithSpecialFields.get(aPoint);

                //nowy warunek, aby nie nastepowalo dublowanie
                if (!field.getTypeOfField().equals("TRIGGERED BY STEPPING")) {
                    field.doSomething(aCreature);
                }

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


    //Metoda ma na celu określenie trasy po której nastąpił ruch,
    public List<Point> examinePath(Point start, Point end) {

        List<Point> path = new ArrayList<>();

        //zmienne określające kierunek w zależności od pozycji
        int dx = Integer.signum(end.getX() - start.getX());
        int dy = Integer.signum(end.getY() - start.getY());


        //współrzędne startowe
        int x = start.getX();
        int y = start.getY();


        while (x != end.getX() || y != end.getY()) {
            if (x != end.getX()) x += dx;
            if (y != end.getY()) y += dy;
            path.add(new Point(x, y));
        }

        return path;
    }

}
