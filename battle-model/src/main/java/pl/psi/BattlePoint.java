package pl.psi;

import lombok.Getter;
import lombok.Value;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
@Value
public class BattlePoint
{
    int x;
    int y;

    public BattlePoint(final int aX, final int aY )
    {
        x = aX;
        y = aY;
    }

    public double distance( BattlePoint aBattlePoint)
    {
        return distance( aBattlePoint.getX(), aBattlePoint.getY() );
    }

    public double distance( double px, double py )
    {
        px -= getX();
        py -= getY();
        return Math.sqrt( px * px + py * py );
    }

}
