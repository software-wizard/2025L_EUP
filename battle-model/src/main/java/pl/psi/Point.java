package pl.psi;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Value
public class Point
{
    int x;
    int y;

    public Point( final int aX, final int aY )
    {
        x = aX;
        y = aY;
    }

    public double distance( Point aPoint )
    {
        return distance( aPoint.getX(), aPoint.getY() );
    }

    public double distance( double px, double py )
    {
        px -= getX();
        py -= getY();
        return Math.sqrt( px * px + py * py );
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
