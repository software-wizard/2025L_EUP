package pl.psi;


import com.google.common.collect.Range;
import lombok.Getter;
import lombok.Setter;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatistic;
import pl.psi.creatures.CreatureStatisticIf;

@Getter
@Setter
public class SpecialField {
    private String typeOfField;
    private CreatureStatisticIf stats;
    private boolean canFly;
    private SpecialField(final CreatureStatisticIf aStats, final String aTypeOfField, final boolean aCanFly){
        stats = aStats;
        typeOfField = aTypeOfField;
        canFly = aCanFly;
    }

    public static void doSomething(String typeOfField, Creature aCreature) {
        if (typeOfField.equals("fieldGivingDmg")) {
            aCreature.setCurrentHp(aCreature.getCurrentHp() - 20);
        }
    }

    public static boolean canFly(String name) {
        if (name.equals("Ghost Dragon") || name.equals("Archangel") || name.equals("Efreeti") || name.equals("Gargoyle")) {
            return true;
        }
        else
        {
            return false;
        }
    }

//    public static boolean canCreaturePassSpecialField() {
//        String name = stats.getName();
//        boolean canFly = SpecialField.canFly(name);
//        return canFly;
//
//    }
}
