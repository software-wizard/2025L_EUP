package pl.psi.gui.shops;


import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.town.Town;

public class ArtifactShopController {

    @FXML private VBox artifactBox;
    private EconomyHero hero;
    private Town town;

    public ArtifactShopController(EconomyHero hero, Town town) {
        this.hero = hero;
        this.town = town;
        loadArtifacts();
    }

    private void loadArtifacts() {
        //TODO jak będą artefakty to podłączyć
    }
}
