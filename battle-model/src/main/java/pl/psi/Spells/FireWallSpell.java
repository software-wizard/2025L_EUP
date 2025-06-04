package pl.psi.Spells;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import pl.psi.Point;
import pl.psi.SpecialField;
import pl.psi.creatures.Creature;


public class FireWallSpell extends Spell {

    private Point castPosition;
    private int size;
    private double power;
    private static final String TYPEOFFIELD = "TRIGGERED BY STEPPING";


    public FireWallSpell(String name, int spellLevel, Point castPosition, double power) {
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

    private void createFireWall(Point castPosition, double damage){
        BiMap<Point, SpecialField> createdFields = HashBiMap.create();

        for (int i=0; i<size;i++){
            Point currentPoint = new Point(castPosition.getX(), castPosition.getY()+i);
            createdFields.put(currentPoint, new FireWall(TYPEOFFIELD,2));
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

        public FireWall(String typeOfField, int duration){
            super(typeOfField);
            this.duration = duration;
        }


        public void doSomething(Creature targetCreature) {
            cast(targetCreature);
        }
    }

}
