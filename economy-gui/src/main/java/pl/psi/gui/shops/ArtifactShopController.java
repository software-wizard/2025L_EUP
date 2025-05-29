package pl.psi.gui.shops;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.artifacts.Artifact;
import pl.psi.hero.artifacts.ArtifactType;
import pl.psi.map.buildings.town.Town;

import java.util.ArrayList;
import java.util.List;

public class ArtifactShopController {

    @FXML
    private VBox artifactBox;

    private EconomyHero hero;
    private Town town;

    private final List<Artifact> availableArtifacts = new ArrayList<>(List.of(
            new Artifact(ArtifactType.ARMOR_OF_WONDER),
            new Artifact(ArtifactType.SWORD_OF_HELLFIRE),
            new Artifact(ArtifactType.CELESTIAL_NECKLACE_OF_BLISS),
            new Artifact(ArtifactType.CROWN_OF_DRAGONTOOTH),
            new Artifact(ArtifactType.SWORD_OF_JUDGEMENT)
    ));


    public ArtifactShopController(EconomyHero hero, Town town) {
        this.hero = hero;
        this.town = town;
    }

    @FXML
    void initialize() {
        loadArtifacts();
    }

    private void loadArtifacts() {
        artifactBox.getChildren().clear();

        for (Artifact artifact : availableArtifacts) {
            HBox artifactRow = new HBox(10);
            artifactRow.setPadding(new Insets(5));

            // Artifact Image
            ImageView imageView = new ImageView(new Image(artifact.getPath()));
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);

            // Artifact Info
            VBox infoBox = new VBox();
            Label nameLabel = new Label(artifact.toString());
            Label statLabel = new Label("Stats: " + artifact.getStats());
            Label costLabel = new Label("Cost: " + artifact.getCost() + " gold");

            infoBox.getChildren().addAll(nameLabel, statLabel, costLabel);

            // Buy Button
            Button buyButton = new Button("Buy");
            buyButton.setOnAction(e -> buyArtifact(artifact));
            if (hero.getResources().getGold() < artifact.getCost()) {
                buyButton.setDisable(true);
            }

            artifactRow.getChildren().addAll(imageView, infoBox, buyButton);
            artifactBox.getChildren().add(artifactRow);
        }
    }

    private void buyArtifact(Artifact artifact) {
        if (hero.canAffordGold(artifact.getCost())) {
            hero.payGold(artifact.getCost());
            hero.addArtifact(artifact);
            availableArtifacts.remove(artifact);
            loadArtifacts();
        }
    }


}
