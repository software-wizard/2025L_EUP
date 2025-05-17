package pl.psi.gui.shops;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.town.Town;

public class SkillShopController {

    @FXML private VBox artifactBox;
    private EconomyHero hero;
    private Town town;

    public SkillShopController(EconomyHero hero, Town town) {
        this.hero = hero;
        this.town = town;
        loadSkills();
    }

    private void loadSkills() {
        //TODO jak będą skille to podłączyć
    }
}