package pl.psi.hero.artifacts;

import lombok.Getter;
import pl.psi.hero.Statistics;

public enum ArtifactType {
    ARMOR_OF_WONDER("Armor of Wonder", "/objects/Artifact_Armor_of_Wonder.gif", new Statistics(1, 1, 1, 1), 4000),
    BLACKSHARD_OF_THE_DEAD_KNIGHT("Blackshard of the Dead Knight", "/objects/Artifact_Blackshard_of_the_Dead_Knight.gif", new Statistics(3, 0, 0, 0), 3000),
    BREASTPLATE_OF_BRIMSTONE("Breastplate of Brimstone", "/objects/Artifact_Breastplate_of_Brimstone.gif", new Statistics(0, 0, 5, 0), 5000),
    BUCKLER_OF_THE_GNOLL_KING("Buckler of the Gnoll King", "/objects/Artifact_Buckler_of_the_Gnoll_King.gif", new Statistics(0, 4, 0, 0), 4000),
    CELESTIAL_NECKLACE_OF_BLISS("Celestial Necklace of Bliss", "/objects/Artifact_Celestial_Necklace_of_Bliss.gif", new Statistics(3, 3, 3, 3), 12000),
    CROWN_OF_DRAGONTOOTH("Crown of Dragontooth", "/objects/Artifact_Crown_of_Dragontooth.gif", new Statistics(4, 0, 0, 4), 8000),
    CROWN_OF_THE_SUPREME_MAGI("Crown of the Supreme Magi", "/objects/Artifact_Crown_of_the_Supreme_Magi.gif", new Statistics(0, 0, 0, 4), 4000),
    DRAGON_SCALE_SHIELD("Dragon Scale Shield", "/objects/Artifact_Dragon_Scale_Shield.gif", new Statistics(3, 3, 0, 0), 6000),
    DRAGON_SCALE_ARMOR("Dragon Scale Armor", "/objects/Artifact_Dragon_Scale_Armor.gif", new Statistics(0, 4, 0, 0), 6000),
    DRAGON_WING_TABARD("Dragon Wing Tabard", "/objects/Artifact_Dragon_Wing_Tabard.gif", new Statistics(0, 0, 2, 2), 4000),
    HELM_OF_CHAOS("Helm of Chaos", "/objects/Artifact_Helm_of_Chaos.gif", new Statistics(0, 0, 3, 0), 3000),
    HELM_OF_HEAVENLY_ENLIGHTENMENT("Helm of Heavenly Enlightenment", "/objects/Artifact_Helm_of_Heavenly_Enlightenment.gif", new Statistics(6, 6, 6, 6), 40000),
    HELM_OF_THE_ALABASTER_UNICORN("Helm of the Alabaster Unicorn", "/objects/Artifact_Helm_of_the_Alabaster_Unicorn.gif", new Statistics(2, 2, 0, 0), 4000),
    RED_DRAGON_FLAME_TONGUE("Red Dragon Flame Tongue", "/objects/Artifact_Red_Dragon_Flame_Tongue.gif", new Statistics(3, 0, 0, 0), 3000),
    SHIELD_OF_THE_DWARVEN_LORDS("Shield of the Dwarven Lords", "/objects/Artifact_Shield_of_the_Dwarven_Lords.gif", new Statistics(0, 5, 0, 0), 5000),
    SHIELD_OF_THE_YAWNING_DEAD("Shield of the Yawning Dead", "/objects/Artifact_Shield_of_the_Yawning_Dead.gif", new Statistics(0, 3, 0, 0), 3000),
    SWORD_OF_HELLFIRE("Sword of Hellfire", "/objects/Artifact_Sword_of_Hellfire.gif", new Statistics(6, 0, 0, 0), 6000),
    SWORD_OF_JUDGEMENT("Sword of Judgement", "/objects/Artifact_Sword_of_Judgement.gif", new Statistics(5, 5, 5, 5), 12000),
    TITANS_CUIRASS("Titan's Cuirass", "/objects/Artifact_Titan's_Cuirass.gif", new Statistics(0, 0, 10, -2), 10000),
    TITANS_GLADIUS("Titan's Gladius", "/objects/Artifact_Titan's_Gladius.gif", new Statistics(12, -3, 0, 0), 10000),
    TITANS_THUNDER("Titan's Thunder", "/objects/Artifact_Titan's_Thunder.gif", new Statistics(9, 9, 8, 8), 40000),
    TUNIC_OF_THE_CYCLOPS_KING("Tunic of the Cyclops King", "/objects/Artifact_Tunic_of_the_Cyclops_King.gif", new Statistics(4, 0, 0, 0), 4000),

    // Add more artifacts as needed

    ;




    @Getter
    private final String imagePath;
    @Getter
    private final Statistics statistics;
    @Getter
    private final String name;
    @Getter
    private final int cost;

    ArtifactType(String name, String imagePath, Statistics stats, int cost) {
        this.name = name;
        this.imagePath = imagePath;
        this.statistics = stats;
        this.cost = cost;
    }
}
