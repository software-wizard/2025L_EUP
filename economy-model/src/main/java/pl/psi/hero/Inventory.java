package pl.psi.hero;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import pl.psi.hero.artifacts.Artifact;

public class Inventory {
    @Getter
    private final Map<Integer, Artifact> artifacts = new HashMap<>();
    private List<Artifact> storage;

    public void addArtifact(Artifact artifact) {
        artifacts.put(artifact.getPosition(), artifact);
    }

    public Artifact getArtifact(int position) {
        return artifacts.get(position);
    }

    public void removeArtifact(int position) {
        artifacts.remove(position);
    }

}
