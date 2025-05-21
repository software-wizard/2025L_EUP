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

    @Getter
    private FieldType typeOfField;
    private Color color;

    protected SpecialField(Color aColor, FieldType aFieldType) {
        color = aColor;
        this.typeOfField = aFieldType;
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
