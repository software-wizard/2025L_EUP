package pl.psi.gui.shops;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.Castle;

public class SpellShopController {

    @FXML private VBox artifactBox;
    private EconomyHero hero;
    private Castle castle;

    public SpellShopController(EconomyHero hero, Castle castle) {
        this.hero = hero;
        this.castle = castle;
        loadSpells();
    }

    private void loadSpells() {
        //TODO jak będą spelle to podłączyć
    }
}
