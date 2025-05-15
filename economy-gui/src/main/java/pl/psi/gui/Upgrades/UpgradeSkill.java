package pl.psi.gui.Upgrades;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.skills.AbstractSkill;

public class UpgradeSkill {

    @FXML
    private ListView<String> upgradeList;

    private EconomyHero hero;

    public void setHero(EconomyHero hero) {
        this.hero = hero;
        refreshUpgrades();
    }

    private void refreshUpgrades() {
        if (hero == null) {
            return;
        }

        upgradeList.getItems().clear();

        for ( AbstractSkill skill : hero.getSkills()) {
            String entry = String.format("%s", skill.getName());
            upgradeList.getItems().add(entry);
        }
    }
    @FXML
    private void handleBuy() {
        String selectedItem = upgradeList.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            return;
        }

        try {
            String skillName = selectedItem.split(" ")[0];
            AbstractSkill selectedSkill = hero.getSkills().stream()
                .filter(skill -> skill.getName().equals(skillName))
                .findFirst()
                .orElse(null);

            if (selectedSkill != null) {
//                hero.upgradeSkill(selectedSkill);
            }
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR, "Error upgrading skill: " + e.getMessage());
            alert.showAndWait();
        }
    }

}
