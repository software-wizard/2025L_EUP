package pl.psi;


import lombok.Getter;
import lombok.Setter;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatisticIf;

import static pl.psi.FieldType.FIELD_GIVING_DMG;
import static pl.psi.FieldType.HUGE_FIELD_GIVING_DMG;

@Getter
@Setter
public class SpecialField {
    public static final String FIELD_GIVING_DMG = "fieldGivingDmg";
    private String typeOfField;
    private CreatureStatisticIf stats;
    private boolean canFly;

    private SpecialField(final CreatureStatisticIf aStats, final String aTypeOfField, final boolean aCanFly) {
        stats = aStats;
        typeOfField = aTypeOfField;
        canFly = aCanFly;
    }

    public static void doSomething(String typeOfField, Creature aCreature) {
        if (typeOfField.equals(FIELD_GIVING_DMG)) {
            aCreature.applyDamage(aCreature, 20);
        }
    }

//    public static void doSomething(FieldType typeOfField, Creature aCreature) {
//        if (typeOfField == FIELD_GIVING_DMG) {
//            aCreature.applyDamage(aCreature, FIELD_GIVING_DMG.getDmg());
//        }
//        if (typeOfField == HUGE_FIELD_GIVING_DMG) {
//            aCreature.applyDamage(aCreature, HUGE_FIELD_GIVING_DMG.getDmg());
//        }
//    }

    public static boolean canFly(String name) {
        if (name.equals("Ghost Dragon") || name.equals("Archangel") || name.equals("Efreeti") || name.equals("Gargoyle")) {
            return true;
        } else {
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
