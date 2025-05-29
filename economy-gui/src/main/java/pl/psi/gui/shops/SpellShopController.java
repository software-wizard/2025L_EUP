package pl.psi.gui.shops;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.town.Town;

public class SpellShopController {

    @FXML private VBox artifactBox;
    private EconomyHero hero;
    private Town town;

    public SpellShopController(EconomyHero hero, Town town) {
        this.hero = hero;
        this.town = town;
        loadSpells();
    }

    private void loadSpells() {
        //TODO jak będą spelle to podłączyć
    }
}
