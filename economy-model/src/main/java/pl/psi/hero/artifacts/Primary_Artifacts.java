package pl.psi.hero.artifacts;

import lombok.Getter;
import pl.psi.hero.Statistics;

public class Primary_Artifacts extends Artifact{
    @Getter
    private Statistics bonuses;
    Primary_Artifacts(String name, String rarity, int attackBonus, int defenseBonus, int powerBonus, int knowledgeBonus, int position) {
        super(name, rarity, position);
        this.bonuses = new Statistics(attackBonus, defenseBonus, powerBonus, knowledgeBonus);
    }

    @Override
    public void applyEffect() {

    }

}
