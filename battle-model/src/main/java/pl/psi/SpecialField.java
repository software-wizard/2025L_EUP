package pl.psi;

import lombok.Getter;

@Getter
public class SpecialField
{
    private String typeOfField;

    private SpecialField(final String aTypeOfField)
    {
        typeOfField = aTypeOfField;
    }

    public static void doSomething(String typeOfField)
    {
        if (typeOfField == "fieldGivingDmg") {
            //Do something
        }
    }
}
