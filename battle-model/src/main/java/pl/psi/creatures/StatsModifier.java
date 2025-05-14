package pl.psi.creatures;

// W module: battle-model
public class StatsModifier {
    private final int attackBonus; // sprawdzic inne statystyki tez
    private final int armorBonus;

    public StatsModifier(int attackBonus, int armorBonus) {
        this.attackBonus = attackBonus;
        this.armorBonus = armorBonus;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public int getArmorBonus() {
        return armorBonus;
    }

    // Dodaj inne statystyki, po przetestowaniu dwóch
}
