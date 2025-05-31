package pl.psi.gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import pl.psi.*;
import com.google.common.collect.Range;
import pl.psi.Hero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.Spells.BuffSpell;
import pl.psi.Spells.DamageSpell;
import pl.psi.Spells.Spell;
import pl.psi.creatures.CreatureStats;
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
        CreatureStats statBuff = CreatureStats.builder()
                .attack(5)
                .armor(0)
                .maxHp(0)
                .moveRange(0)
                .name("Buff")
                .description("Powerful boost")
                .tier(1)
                .damage(Range.closed(0, 0))
                .isUpgraded(false)
                .build();
        List<Spell> spells = List.of(
            new DamageSpell("Damage", 1, 1),
            new BuffSpell("Buff", 1, 3, statBuff)
        );

        final Hero ret = new Hero( List.of( new NecropolisFactory().create( true, 1, 5 ) ),spells );
        return ret;
    }

    private Hero createP1()
    {
        CreatureStats statBuff = CreatureStats.builder()
                .attack(5)
                .armor(0)
                .maxHp(0)
                .moveRange(0)
                .name("Buff")
                .description("Powerful boost")
                .tier(1)
                .damage(Range.closed(0, 0))
                .isUpgraded(false)
                .build();
        List<Spell> spells = List.of(
                new DamageSpell("Damage", 1, 1),
                new BuffSpell("Buff", 1, 3, statBuff)
        );


        final Hero ret = new Hero( List.of( new NecropolisFactory().create( false, 1, 5 ) ), spells );
        return ret;
    }

    private BiMap <BattlePoint, SpecialField > createSpecialFields()
    {
        final BiMap < BattlePoint, SpecialField > specialFields = HashBiMap.create();
        specialFields.put(new BattlePoint(5, 5), new DmgField());
        specialFields.put(new BattlePoint(3, 8), new SpellField());
        specialFields.put(new BattlePoint(3, 8), new SpellField());
        specialFields.put(new BattlePoint(2,4), new FieldCanOnlyBeFlown());
        specialFields.put(new BattlePoint(4, 9), new DebuffField());
        return specialFields;
    }

}
