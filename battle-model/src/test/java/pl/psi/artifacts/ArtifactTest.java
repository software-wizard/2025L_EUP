package pl.psi.artifacts;
import org.junit.jupiter.api.Test;
import pl.psi.Hero;
import pl.psi.creatures.Statistics;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ArtifactTest {
    @Test
    void testSingleStatBoostArtifact() {

        Hero hero = new Hero("Sir Christian",new Statistics(20, 10, 10, 10), List.of());
        Artifact sword1 = new Artifact("Sword of Hellfire",6,0,0,0);// name, attack, defense,
        //power,
        sword1.apply(hero);

        assertThat(hero.getAttack()).isEqualTo(26);
        assertThat(hero.getDefense()).isEqualTo(10);
        assertThat(hero.getPower()).isEqualTo(10);
        assertThat(hero.getKnowledge()).isEqualTo(10);
    }

    @Test
    void testMultipleStatBoostArtifact2() {
        Hero hero1 = new Hero("Tyris",new Statistics(20, 10, 10, 10), List.of());
        Artifact sword2 = new Artifact("Sword of Judgement",5,5,5,5);

        sword2.apply(hero1);

        assertThat(hero1.getAttack()).isEqualTo(25);
        assertThat(hero1.getDefense()).isEqualTo(15);
        assertThat(hero1.getPower()).isEqualTo(15);
        assertThat(hero1.getKnowledge()).isEqualTo(15);
    }
}

