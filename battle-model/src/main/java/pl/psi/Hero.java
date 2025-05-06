package pl.psi;

import java.util.ArrayList;
import java.util.List;

///////////import pl.psi.artifacts.Artifact;
import pl.psi.creatures.Creature;

import lombok.Getter;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Hero {
    private String name;
//    @Getter
//    private final Statistics baseStatistics;
//    @Getter
//    private final List<Creature> creatures;
//
//    private final List<Artifact> artifacts = new ArrayList<>();

    public Hero(String name, final List<Creature> aCreatures) {
        this.name = name;
//        this.baseStatistics = baseStatistics;
//        this.creatures = aCreatures;
    }

//    public void addArtifact(Artifact artifact) {
//        artifacts.add(artifact);
//    }
//
//    public List<Artifact> getArtifacts() {
//        return List.copyOf(artifacts);
//    }
//
//    public Statistics getTotalStatistics() {
//        Statistics total = new Statistics(
//                baseStatistics.getAttack(),
//                baseStatistics.getDefense(),
//                baseStatistics.getPower(),
//                baseStatistics.getKnowledge()
//        );
//        for (Artifact artifact : artifacts) {
//            total.increase(artifact.getBonuses());
//        }
//        return total;
//    }
//
//    public int getAttack() {
//        return getTotalStatistics().getAttack();
//    }
//
//    public int getDefense() {
//        return getTotalStatistics().getDefense();
//    }
//
//    public int getPower() {
//        return getTotalStatistics().getPower();
//    }
//
//    public int getKnowledge() {
//        return getTotalStatistics().getKnowledge();
//    }
}
