package pl.psi.gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import pl.psi.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.creatures.NecropolisFactory;

public class Start extends Application
{

    public Start()
    {

    }

    static void main( final String[] args )
    {
        launch( args );
    }

    @Override
    public void start( final Stage primaryStage )
    {
        Scene scene = null;
        try
        {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation( Start.class.getClassLoader()
                .getResource( "fxml/main-battle.fxml" ) );
            loader.setController( new MainBattleController( createP1(), createP2(), new HashMap<>(), createSpecialFields() ) );
            scene = new Scene( loader.load() );
            primaryStage.setScene( scene );
            primaryStage.setX( 5 );
            primaryStage.setY( 5 );
            primaryStage.show();
        }
        catch( final IOException aE )
        {
            aE.printStackTrace();
        }
    }

    private Hero createP2()
    {
        final Hero ret = new Hero( List.of( new NecropolisFactory().create( true, 1, 5,0 ) ) );
        return ret;
    }

    private Hero createP1()
    {
        final Hero ret = new Hero( List.of( new NecropolisFactory().create( false, 1, 5 ,0) ) );
        return ret;
    }

    private BiMap < Point, SpecialField > createSpecialFields()
    {
        final BiMap < Point, SpecialField > specialFields = HashBiMap.create();
        specialFields.put(new Point(5, 5), new DmgField());
        specialFields.put(new Point(3, 8), new SpellField());
        specialFields.put(new Point(2,4), new FieldCanOnlyBeFlown());
        return specialFields;
    }

}
