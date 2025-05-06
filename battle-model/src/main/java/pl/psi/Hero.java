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

    public Hero(String name, final List<Creature> aCreatures) {
        this.name = name;

    }

}
