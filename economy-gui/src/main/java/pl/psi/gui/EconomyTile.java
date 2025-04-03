package pl.psi.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EconomyTile extends StackPane
{

    private final Rectangle rect;
    private final Label label;

    EconomyTile( final String aName )
    {
        rect = new Rectangle( 60, 60 );
        rect.setFill( Color.WHITE );
        rect.setStroke( Color.RED );
        getChildren().add( rect );
        label = new Label( aName );
        getChildren().add( label );
    }

    void setName( final String aName )
    {
        label.setText( aName );
    }

    void setBackground( final Color aColor )
    {
        rect.setFill( aColor );
    }
}

