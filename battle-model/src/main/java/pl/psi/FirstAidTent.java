package pl.psi;

import java.util.List;
import pl.psi.creatures.Creature;

public class FirstAidTent {

    public void healCreature(Creature creature) {
        // Sprawdzenie, czy jednostka jest ranna
        if (creature.getCurrentHp() < creature.getMaxHp()) {
            int healAmount = Math.min(20, creature.getMaxHp() - creature.getCurrentHp());
            creature.setCurrentHp(creature.getCurrentHp() + healAmount);
        }
    }

    public void healArmy(List<Creature> army) {
        Creature mostInjured = null;
        for (Creature creature : army) {
            // Wybieramy jednostkę z najmniejszym HP, która jest ranna
            if (creature.getCurrentHp() < creature.getMaxHp()) {
                if (mostInjured == null || creature.getCurrentHp() < mostInjured.getCurrentHp()) {
                    mostInjured = creature;
                }
            }
        }

        // Leczymy najbardziej ranną jednostkę
        if (mostInjured != null) {
            healCreature(mostInjured);
        }
    }
}
