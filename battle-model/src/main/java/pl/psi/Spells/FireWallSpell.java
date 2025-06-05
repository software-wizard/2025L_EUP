package pl.psi.Spells;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import pl.psi.BattlePoint;
import pl.psi.SpecialField;
import pl.psi.creatures.Creature;

import static pl.psi.SpecialField.FieldName.FIRE_FIELD;


public class FireWallSpell extends Spell {

    private BattlePoint castPosition;
    private int size;
    private double power;
    private static final String TYPEOFFIELD = "TRIGGERED BY STEPPING";


    public FireWallSpell(String name, int spellLevel, BattlePoint castPosition, double power) {
        super(name, spellLevel);

        this.duration = 2;
        this.power = power;
        this.castPosition = castPosition;

        if (spellLevel == 1) {
            this.size = 2;
        } else if (spellLevel == 2) {
            this.size = 3;
        }

        createFireWall(castPosition, fireWallDamageCalculator());
    }

    private void createFireWall(BattlePoint castPosition, double damage) {
        BiMap<BattlePoint, SpecialField> createdFields = HashBiMap.create();

        for (int i = 0; i < size; i++) {
            BattlePoint currentBattlePoint = new BattlePoint(castPosition.getX(), castPosition.getY() + i);
            createdFields.put(currentBattlePoint, new FireWall(2));
        }
        //nie wiem jak dostać się do planszy w gameengine

    }

    //chwilowo nie uzywane, zastanawiam sie nad implementacja
    public double fireWallDamageCalculator() {
        double levelBasedDamageBonus;
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
        return power * 10 + levelBasedDamageBonus;
    }

    @Override
    public void cast(Creature targetCreature) {
        targetCreature.applyMagicDamage(this);

    }

    public class FireWall extends SpecialField {


        int duration;

        public FireWall(int duration) {
            super(Color.RED, FIRE_FIELD);
            this.duration = duration;
        }


        public void doSomething(Creature targetCreature) {
            cast(targetCreature);
        }
    }

}
