package pl.psi.gui.shops;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pl.psi.gui.WindowManager;
import pl.psi.hero.EconomyHero;
import pl.psi.map.buildings.Castle;

public class ShopCategoryController {

    private EconomyHero hero;
    private Castle castle;

    @FXML private Button creaturesButton;
    @FXML private Button spellsButton;
    @FXML private Button artifactsButton;
    @FXML private Button skillsButton;

    public ShopCategoryController() {
        // Domyślny konstruktor wymagany przez FXMLLoader
    }

    public void init(EconomyHero hero, Castle castle) {
        this.hero = hero;
        this.castle = castle;

        // Można też ustawić onAction tutaj, jeśli initialize() nie działa jak trzeba
        creaturesButton.setOnAction(e -> WindowManager.openCreatureShop(hero, castle));
        spellsButton.setOnAction(e -> WindowManager.openSpellShop(hero, castle));
        artifactsButton.setOnAction(e -> WindowManager.openArtifactShop(hero, castle));
        skillsButton.setOnAction(e -> WindowManager.openSkillShop(hero, castle));
    }

    @FXML
    private void initialize() {
        // Możesz zostawić puste lub z logiem inicjalizacji
    }
}
