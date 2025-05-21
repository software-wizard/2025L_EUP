package pl.psi;

import lombok.Getter;

public enum FieldType {
    FIELD_GIVING_DMG(20),
    HUGE_FIELD_GIVING_DMG(200),
    TRIGGERED_BY_STEPPING(20);
    @Getter
    private final int dmg;

    FieldType(int aDmg) {
        dmg = aDmg;
    }


}
