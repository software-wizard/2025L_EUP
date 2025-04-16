package pl.psi.gui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    public void setImage(String imagePath) {
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(59); // resize to fit tile
        imageView.setFitHeight(59);
        getChildren().add(imageView); // stack it on the tile
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

