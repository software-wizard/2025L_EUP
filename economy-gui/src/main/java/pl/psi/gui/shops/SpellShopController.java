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
import pl.psi.hero.artifacts.EconomySpell;
import pl.psi.map.buildings.town.Town;
import pl.psi.map.buildings.town.TownBuilding;

import java.util.List;

import static pl.psi.gui.EconomyBoardStart.SPELL_NAME;

public class SpellShopController {

    @FXML
    private VBox spellBox;
    private EconomyHero hero;
    private Town town;

    public SpellShopController(EconomyHero hero, Town town) {
        this.hero = hero;
        this.town = town;
    }

    @FXML
    void initialize() {
        loadSpells(town);
    }

    private void loadSpells(Town aTown) {
        List<EconomySpell> spells = List.of(new EconomySpell(SPELL_NAME));
        if (aTown.hasBuilt(TownBuilding.MAGE_GUILD_LVL_1)) {
            spellBox.getChildren().clear();

            for (EconomySpell spell : spells) {
                HBox artifactRow = new HBox(10);
                artifactRow.setPadding(new Insets(5));

                // Artifact Image
                ImageView imageView = new ImageView(new Image(spell.getPath()));
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);

                // Artifact Info
                VBox infoBox = new VBox();
                Label nameLabel = new Label(spell.toString());
                Label costLabel = new Label("Cost: " + spell.getCost() + " gold");

                infoBox.getChildren().addAll(nameLabel, costLabel);

                // Buy Button
                Button buyButton = new Button("Buy");
                buyButton.setOnAction(e -> buyArtifact(spell));
                if (hero.getResources().getGold() < spell.getCost()) {
                    buyButton.setDisable(true);
                }

                artifactRow.getChildren().addAll(imageView, infoBox, buyButton);
                spellBox.getChildren().add(artifactRow);
            }
        }
    }

    private void buyArtifact(EconomySpell artifact) {
        if (hero.canAffordGold(artifact.getCost())) {
            hero.payGold(artifact.getCost());
            hero.addSpell(artifact);
        }
    }
}