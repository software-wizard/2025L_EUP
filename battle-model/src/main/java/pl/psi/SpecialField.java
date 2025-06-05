package pl.psi;


import lombok.Getter;
import lombok.Setter;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatisticIf;

@Getter
@Setter
public abstract class SpecialField {
    public enum Color{
        CYAN, BROWN, ORANGE, YELLOW, GRAY, RED
    }

    public enum FieldName{
        DMG_FIELD, SPELL_FIELD, BUFF_FIELD, FIELD_CAN_ONLY_BE_FLOWN, DEBUFF_FIELD, FIRE_FIELD
    }

    @Getter
    private Color color;
    @Getter
    private FieldName fieldName;

    protected SpecialField(Color aColor, FieldName aFieldName) {
        color = aColor;
        fieldName = aFieldName;
    }

    public abstract void doSomething(Creature aCreature);

    public static boolean canFly(String name) {
        if (name.equals("Ghost Dragon") || name.equals("Archangel") || name.equals("Efreeti") || name.equals("Gargoyle")) {
            return true;
        } else {
            return false;
        }
    }

    boolean canInteract(Creature aCreature) {
//        if((aCreature.canFly(aCreature.getName())) && (SpecialField.getFieldName() == SpecialField.FieldName.FIELD_CAN_ONLY_BE_FLOWN)){
            return true;
//        }
//        return false;
    }



//    public static boolean canCreaturePassSpecialField() {
//        String name = stats.getName();
//        boolean canFly = SpecialField.canFly(name);
//        return canFly;
//
//    }
}
