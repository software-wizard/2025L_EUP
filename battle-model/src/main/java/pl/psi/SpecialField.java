package pl.psi;

import lombok.Getter;
import pl.psi.creatures.Creature;

@Getter
public class SpecialField
{
    @Getter
    private final String typeOfField;


    protected SpecialField(final String aTypeOfField)
    {
        typeOfField = aTypeOfField;
    }

    public void doSomething(Creature aCreature) {
    }
}
