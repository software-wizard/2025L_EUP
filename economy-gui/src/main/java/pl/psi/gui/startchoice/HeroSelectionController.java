package pl.psi.gui.startchoice;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import lombok.Setter;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.hero.EconomyHero;
import pl.psi.gui.startchoice.HeroType;
import pl.psi.hero.Statistics;
import pl.psi.map.resources.Resources;

import java.util.List;
import java.util.function.BiConsumer;

public class HeroSelectionController {

    @FXML private ComboBox<String> fraction1Combo;
    @FXML private ComboBox<String> fraction2Combo;
    @FXML private ComboBox<String> hero1Combo;
    @FXML private ComboBox<String> hero2Combo;


    @Setter
    private Stage dialogStage;
    @Setter
    private BiConsumer<EconomyHero, EconomyHero> onHeroesSelected;

    @FXML
    private void initialize() {
        for (EconomyHero.Fraction fraction : EconomyHero.Fraction.values()) {
            fraction1Combo.getItems().add(fraction.name());
            fraction2Combo.getItems().add(fraction.name());
        }

        fraction1Combo.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            hero1Combo.getItems().setAll(getHeroNamesForFraction(newVal));
            hero1Combo.getSelectionModel().selectFirst();
        });
        fraction2Combo.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            hero2Combo.getItems().setAll(getHeroNamesForFraction(newVal));
            hero2Combo.getSelectionModel().selectFirst();
        });

        fraction1Combo.getSelectionModel().selectFirst();
        fraction2Combo.getSelectionModel().selectFirst();
    }

    @FXML
    private void onStartGameClicked() {
        String frac1 = fraction1Combo.getValue();
        String frac2 = fraction2Combo.getValue();

        String hero1 = hero1Combo.getValue();
        String hero2 = hero2Combo.getValue();

        EconomyHero aHero1 = createHeroFromSelection(frac1, hero1);
        EconomyHero aHero2 = createHeroFromSelection(frac2, hero2);

        if (onHeroesSelected != null) {
            onHeroesSelected.accept(aHero1, aHero2);
        }
    }

    private EconomyHero createHeroFromSelection(String fractionName, String heroName) {
        EconomyHero.Fraction fraction = EconomyHero.Fraction.valueOf(fractionName.toUpperCase());
        Resources resources = new Resources(3000, 50, 50, 50, 50, 50, 50); // common starting resources

        for (HeroType heroType : HeroType.values()) {
            if (heroType.displayName.equals(heroName) && heroType.fraction == fraction) {
                HeroType.HeroData data = heroType.getData();
                EconomyHero hero = new EconomyHero(fraction, resources, data.getStats());
                data.getCreatures().forEach(hero::addCreature);
                return hero;
            }
        }
        return null;
    }


    private List<String> getHeroNamesForFraction(String fractionName) {
        // Convert fractionName to enum and fetch the hero names
        try {
            EconomyHero.Fraction fraction = EconomyHero.Fraction.valueOf(fractionName.toUpperCase());
            return HeroType.getHeroNamesForFraction(fraction);
        } catch (IllegalArgumentException e) {
            // If the fraction name is invalid, return an empty list or handle the exception
            return List.of();
        }
    }



}
