package pl.psi.Spells;

import pl.psi.Point;
import pl.psi.creatures.Creature;
import java.util.ArrayList;


public class FireWallSpell extends Spell{

    private Point castPosition;
    private int size;
    private double power;
    //List gathers information about creatures that passed through the firewall
    private ArrayList<Creature> targetCreatures = new ArrayList<>();


    public FireWallSpell(String name, int spellLevel, int duration, Point castPosition, double power){
        super(name, spellLevel, duration);

        this.power = power;
        this.castPosition = castPosition;

        if (spellLevel==1){
            this.size = 2;
        }else if (spellLevel==2){
            this.size = 3;
        }

    }


    //

    public void burnCreatures(ArrayList<Creature> targetCreatures){
        for (Creature target : targetCreatures){
            cast(target);
        }
    }

    public double fireWallDamageCalculator(){
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
        return  power * 10 + levelBasedDamageBonus;
    }


    @Override
    public void cast(Creature targetCreature) {
        final double hpToSubtract = fireWallDamageCalculator();
        int hp = (int) (targetCreature.getCurrentHp() - hpToSubtract);
        targetCreature.setCurrentHp(hp);
    }
}
