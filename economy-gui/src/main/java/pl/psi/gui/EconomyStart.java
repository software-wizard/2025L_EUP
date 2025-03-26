package pl.psi.gui;

import pl.psi.hero.EconomyHero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EconomyStart extends Application
{

    public static void main( final String[] args )
    {
        launch();
    }

    @Override
    public void start( final Stage aStage ) throws Exception
    {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation( getClass().getClassLoader()
            .getResource( "fxml/eco.fxml" ) );
        loader.setController( new EcoController( new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 3000 ),
            new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 3000 ) ) );
        final Scene scene = new Scene( loader.load() );
        aStage.setScene( scene );
        aStage.setX( 5 );
        aStage.setY( 5 );
        aStage.show();
    }
}
