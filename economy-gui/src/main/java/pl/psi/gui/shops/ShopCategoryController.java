package pl.psi.gui.shops;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pl.psi.gui.WindowManager;
import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.town.Town;

public class ShopCategoryController {

    private EconomyHero hero;
    private Town town;

    @FXML private Button creaturesButton;
    @FXML private Button spellsButton;
    @FXML private Button artifactsButton;
    @FXML private Button skillsButton;

    public ShopCategoryController() {
        // Domyślny konstruktor wymagany przez FXMLLoader
    }

    public void init(EconomyHero hero, Town town) {
        this.hero = hero;
        this.town = town;

        // Można też ustawić onAction tutaj, jeśli initialize() nie działa jak trzeba
        creaturesButton.setOnAction(e -> WindowManager.openCreatureShop(hero, town));
        spellsButton.setOnAction(e -> WindowManager.openSpellShop(hero, town));
        artifactsButton.setOnAction(e -> WindowManager.openArtifactShop(hero, town));
        skillsButton.setOnAction(e -> WindowManager.openSkillShop(hero, town));
    }

    @FXML
    private void initialize() {
        // Możesz zostawić puste lub z logiem inicjalizacji
    }
}
