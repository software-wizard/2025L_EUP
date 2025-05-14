package pl.psi.gui.shops;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.Castle;

public class SkillShopController {

    @FXML private VBox artifactBox;
    private EconomyHero hero;
    private Castle castle;

    public SkillShopController(EconomyHero hero, Castle castle) {
        this.hero = hero;
        this.castle = castle;
        loadSkills();
    }

    private void loadSkills() {
        //TODO jak będą skille to podłączyć
    }
}