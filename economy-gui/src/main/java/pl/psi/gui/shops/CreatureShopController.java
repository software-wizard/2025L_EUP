package pl.psi.gui.shops;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import lombok.Setter;
import pl.psi.EconomyEngine;
import pl.psi.creatures.EconomyCreature;
import pl.psi.creatures.EconomyNecropolisFactory;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pl.psi.map.buildings.town.UpgradeBuildings;
import pl.psi.gui.CreatureButton;
import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.town.Town;

public class CreatureShopController implements PropertyChangeListener
{
    private final EconomyEngine economyEngine;
    @Setter
    private Town town;
    @FXML
    HBox heroStateHBox;
    @FXML
    HBox shopsBox;
    @FXML
    Button readyButton;
    @FXML
    Label playerLabel;
    @FXML
    Label currentGoldLabel;


    public CreatureShopController(final EconomyHero aHero1, final Town town) {
        economyEngine = new EconomyEngine(aHero1);
        this.town = town;
    }


    @FXML
    void initialize()
    {
        refreshGui();
        economyEngine.addObserver( EconomyEngine.HERO_BOUGHT_CREATURE, this );
    }

    public void refreshGui()
    {
        playerLabel.setText( economyEngine.getHero()
            .toString() );
        currentGoldLabel.setText( String.valueOf( economyEngine.getHero()
                .getResources().getGold() ) );
        shopsBox.getChildren()
            .clear();
        heroStateHBox.getChildren()
            .clear();

        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        final VBox creatureShop = new VBox();

        for (int i = 1; i < 8; i++) {
            EconomyCreature base = factory.create(false, i, 1);
            EconomyCreature upgraded = factory.create(true, i, 1);

            CreatureButton baseButton = new CreatureButton(this, factory, false, i);
            CreatureButton upgradedButton = new CreatureButton(this, factory, true, i);

            UpgradeBuildings.getBuildingForCreature(base.getStats())
                    .ifPresentOrElse(
                            building -> {
                                if (!town.hasBuilt(building)) {
                                    baseButton.setDisable(true);
                                }
                            },
                            () -> baseButton.setDisable(true)
                    );

            UpgradeBuildings.getBuildingForCreature(upgraded.getStats())
                    .ifPresentOrElse(
                            building -> {
                                if (!town.hasBuilt(building)) {
                                    upgradedButton.setDisable(true);
                                }
                            },
                            () -> upgradedButton.setDisable(true)
                    );


            creatureShop.getChildren().addAll(baseButton, upgradedButton);
        }
        shopsBox.getChildren().add(creatureShop);


        final VBox creaturesBox = new VBox();
        economyEngine.getHero()
            .getCreatures()
            .forEach( c -> {
                final HBox tempHbox = new HBox();
                tempHbox.getChildren()
                    .add( new Label( String.valueOf( c.getAmount() ) ) );
                tempHbox.getChildren()
                    .add( new Label( c.getName() ) );
                creaturesBox.getChildren()
                    .add( tempHbox );
            } );
        heroStateHBox.getChildren()
            .add( creaturesBox );
    }

    public void buy( final EconomyCreature aCreature )
    {
        economyEngine.buy( aCreature );
    }

    @Override
    public void propertyChange( final PropertyChangeEvent aPropertyChangeEvent )
    {
        refreshGui();
    }
}
