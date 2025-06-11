package pl.psi.hero.skills;

public class SkillsFactory {
    public static AbstractSkill createSkill(SkillName skillName) {
        switch (skillName) {
            case ARMORER:
                return new ArmorerSkill();
            case OFFENCE:
                return new OffenceSkill();
            default:
                throw new IllegalArgumentException("Unknown skill: " + skillName);
        }
    }
}
