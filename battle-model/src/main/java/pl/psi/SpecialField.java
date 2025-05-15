package pl.psi;


import lombok.Getter;
import lombok.Setter;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatisticIf;

@Getter
@Setter
public abstract class SpecialField {
    public enum Color{
        BLUE, BROWN
    }

    public static final String FIELD_GIVING_DMG = "fieldGivingDmg";
    @Getter
    private Color color;

    protected SpecialField(Color aColor) {
        color = aColor;
    }

    public abstract void doSomething(Creature aCreature);

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
