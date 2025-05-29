package pl.psi.gui.Upgrades;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.skills.AbstractSkill;
import pl.psi.hero.skills.SkillLevel;
import pl.psi.hero.skills.SkillName;

import java.util.List;
import java.util.Collections;


public class UpgradeSkill {

    @FXML
    private ListView<String> upgradeList;

    private List<String> availableSkills;

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

        for (AbstractSkill skill : hero.getSkills()) {
            if (skill.getLevel().equals(SkillLevel.EXPERT)) {continue;}

            String entry = String.format("%s", skill.getName());
            availableSkills.add(entry);
        }
        for (SkillName skill : SkillName.values()) {
            if (availableSkills.stream().noneMatch(s -> s.equals(skill.name()))) {
                String entry = String.format("%s", skill.name());
                availableSkills.add(entry);
            }
        }
        Collections.shuffle(availableSkills);
        upgradeList.getItems().add(availableSkills.get(0));
        upgradeList.getItems().add(availableSkills.get(1));
    }

    @FXML
    private void handleUpgrade() {
        String selectedItem = upgradeList.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            return;
        }

        try {
            String skillName = selectedItem.split(" ")[0];
            AbstractSkill selectedSkill = hero.getSkills().stream()
                    .filter(skill -> skill.getName().toString().equals(skillName))
                    .findFirst()
                    .orElse(null);

            if (selectedSkill != null) {
                selectedSkill.upgrade();
                showAlert(AlertType.INFORMATION, "Upgrade Purchased",
                        selectedSkill + " unlocked!");
            }
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR, "Error upgrading skill: " + e.getMessage());
            alert.showAndWait();
        }
    }
    private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
