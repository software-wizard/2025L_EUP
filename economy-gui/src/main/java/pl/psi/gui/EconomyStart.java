package pl.psi.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.gui.shops.CreatureShopController;
import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.town.Town;
import pl.psi.map.resources.Resources;
import pl.psi.hero.Statistics;

public class EconomyStart extends Application
{

    public static void main( final String[] args )
    {
        launch();
    }

    @Override
    public void start( final Stage aStage ) throws Exception
    {
        Statistics aStats = new Statistics(10, 10, 10, 10);///
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation( getClass().getClassLoader()
            .getResource("fxml/creature-shop.fxml") );
        loader.setController( new CreatureShopController( new EconomyHero( EconomyHero.Fraction.NECROPOLIS, new Resources(3000,0,0,0,0,0,0), aStats), new Town()));
        final Scene scene = new Scene( loader.load() );
        aStage.setScene( scene );
        aStage.setX( 5 );
        aStage.setY( 5 );
        aStage.show();
    }
}
