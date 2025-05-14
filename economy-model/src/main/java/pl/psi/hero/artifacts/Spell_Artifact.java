package pl.psi.hero.artifacts;

public class Spell_Artifact extends Artifact{
    private ISpellEffectOnArtifact spellEffectOnArtifact;

    Spell_Artifact(String name, String rarity,int position, ISpellEffectOnArtifact spellEffectOnArtifact) {
        super(name, rarity, position);
        this.spellEffectOnArtifact = spellEffectOnArtifact;
    }

    @Override
    public void applyEffect() {
        spellEffectOnArtifact.apply();
    }
}
