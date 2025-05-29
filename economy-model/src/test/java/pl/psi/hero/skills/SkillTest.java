package pl.psi.hero.skills;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkillTest {
    @Disabled
    @Test
    void testOffenceSkillUpgrade() {
        OffenceSkill offenceSkill = new OffenceSkill();

        assertEquals(SkillLevel.BASIC, offenceSkill.getLevel());
        assertEquals(0.1f, offenceSkill.getFactor(), 0.01);

        offenceSkill.upgrade();
        assertEquals(SkillLevel.ADVANCED, offenceSkill.getLevel());
        assertEquals(0.2f, offenceSkill.getFactor(), 0.01);

        offenceSkill.upgrade();
        assertEquals(SkillLevel.EXPERT, offenceSkill.getLevel());
        assertEquals(0.3f, offenceSkill.getFactor(), 0.01);

        assertThrows(IllegalStateException.class, offenceSkill::upgrade);
    }
    @Disabled
    @Test
    void testArmorerSkillUpgrade() {
        ArmorerSkill armorerSkill = new ArmorerSkill();

        assertEquals(SkillLevel.BASIC, armorerSkill.getLevel());
        assertEquals(0.1f, armorerSkill.getFactor(), 0.01);

        armorerSkill.upgrade();
        assertEquals(SkillLevel.ADVANCED, armorerSkill.getLevel());
        assertEquals(0.2f, armorerSkill.getFactor(), 0.01);

        armorerSkill.upgrade();
        assertEquals(SkillLevel.EXPERT, armorerSkill.getLevel());
        assertEquals(0.3f, armorerSkill.getFactor(), 0.01);

        assertThrows(IllegalStateException.class, armorerSkill::upgrade);
    }
}
