package pl.psi.creatures;

public class ArtifactBonusEffect implements BattleEffect {
    private final StatsModifier statsModifier;

    // Konstruktor przyjmujący bonusy z artefaktu
    public ArtifactBonusEffect(StatsModifier statsModifier) {
        this.statsModifier = statsModifier;
    }

    // Modyfikuje statystyki stworzenia przy użyciu bonusów z artefaktu
    @Override
    public CreatureStatisticIf modify(CreatureStatisticIf baseStats) {
        // Tworzymy zmodyfikowane statystyki na podstawie podstawowych statystyk i bonusów z artefaktu
        return new ModifiedCreatureStats(baseStats, statsModifier);
    }
}
