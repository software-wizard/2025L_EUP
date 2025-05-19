package pl.psi.gui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.artifacts.Artifact;
import pl.psi.hero.artifacts.ArtifactType;

public class EquipmentController {

    @FXML
    private VBox equipmentList;

    private final EconomyHero hero;

    public EquipmentController(EconomyHero hero) {
        this.hero = hero;
    }

    @FXML
    private void initialize() {
        showEquipment();
    }

    private void showEquipment() {
        System.out.println("Hero has " + hero.getArtifacts().size() + " artifacts.");



        equipmentList.getChildren().clear();

        for (Artifact artifact : hero.getArtifacts()) {

            System.out.println("Loading image: " + artifact.getPath());
            System.out.println("Resolved: " + getClass().getResource(artifact.getPath()));

            VBox artifactBox = new VBox(2);
            artifactBox.setStyle("-fx-padding: 5; -fx-border-color: #ccc;");

            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(artifact.getPath())));
            imageView.setFitWidth(50);
            imageView.setPreserveRatio(true);

            Text stats = new Text(artifact.getStats().toString());
            Text name = new Text(artifact.toString());

            artifactBox.getChildren().addAll(imageView, name, stats);
            equipmentList.getChildren().add(artifactBox);
        }
    }
}
