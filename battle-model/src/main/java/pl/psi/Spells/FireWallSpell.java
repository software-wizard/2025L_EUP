package pl.psi.Spells;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import pl.psi.*;
import pl.psi.creatures.Creature;




public class FireWallSpell extends Spell {
    private int size;
    private int power;

    public FireWallSpell(String name, int spellLevel, BattlePoint castPosition, int power, Board aBoard) {
        super(name, spellLevel);

        this.duration = 2;
        this.power = power;

        if (spellLevel == 1) {
            this.size = 2;
        } else if (spellLevel == 2) {
            this.size = 3;
        }

        createFireWall(castPosition, fireWallDamageCalculator(), aBoard);
    }

    private void createFireWall(BattlePoint castPosition, int aDamage, Board aBoard){
        BiMap<BattlePoint, SpecialField> createdFields = HashBiMap.create();

        for (int i=0; i<size;i++){
            BattlePoint currentBattlePoint = new BattlePoint(castPosition.getX(), castPosition.getY()+i);
            createdFields.put(currentBattlePoint, new FireWall(aDamage));
        }
        aBoard.addSpecialFieldOpen(createdFields);
    }

    public int fireWallDamageCalculator() {
        int levelBasedDamageBonus;
        switch (spellLevel) {
            case 2:
                levelBasedDamageBonus = 20;
                break;
            case 3:
                levelBasedDamageBonus = 50;
                break;
            default:
                levelBasedDamageBonus = 10;
                break;
        }
        return (int) power * 10 + levelBasedDamageBonus;
    }

    @Override
    public void cast(Creature targetCreature) {
    }
}
