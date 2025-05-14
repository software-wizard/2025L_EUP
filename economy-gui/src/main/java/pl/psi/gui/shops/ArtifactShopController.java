package pl.psi.gui.shops;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pl.psi.hero.Artifact;
import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.Castle;

import java.util.List;

public class ArtifactShopController {

    @FXML private VBox artifactBox;
    private EconomyHero hero;
    private Castle castle;

    public ArtifactShopController(EconomyHero hero, Castle castle) {
        this.hero = hero;
        this.castle = castle;
        loadArtifacts();
    }

    private void loadArtifacts() {
        //TODO jak będą artefakty to podłączyć
    }
}
