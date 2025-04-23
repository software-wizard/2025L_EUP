package pl.psi;


import com.google.common.collect.Range;
import lombok.Getter;
import lombok.Setter;
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

    public static void doSomething(String typeOfField) {
        if (typeOfField == "fieldGivingDmg") {
            //Do something
        }
    }

    public static void canFly() {
        String name = stats.getName();

        if (name.equals("Ghost Dragon") || name.equals("Archangel") || name.equals("Efreeti") || name.equals("Gargoyle")) {
            SpecialField.setCanFly(true);
        }
        else
        {
            SpecialField.setCanFly(false);
        }
    }

    public static void canCreaturePassSpecialField() {
        String name = stats.getName();
        boolean canFly = SpecialField.getCanFly();
        if(canFly)
        {
            //pass
        }

    }
}
