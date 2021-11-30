package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.agent.ACharacter;
import edu.rice.comp504.model.agent.Ghost;

import java.util.ArrayList;

public class DeathStrategy implements IUpdateStrategy {

    private static IUpdateStrategy singleton;
    /**
     * Make the only random chase strategy.
     *
     * @return A random chase strategy
     */
    public static IUpdateStrategy make() {
        if (singleton == null) {
            singleton = new DeathStrategy();
        }
        return singleton;
    }

    @Override
    public String getName() {
        return "death";
    }

    @Override
    public void updateState(ACharacter context, ACharacter context2) {
        Ghost ghost = (Ghost) context;
        if (ghost.getDeathPath() == null || ghost.getDeathPath().isEmpty()) {
            ghost.setPath(AStarAlgorithm.aStarSearch(ghost.getLoc(), ghost.getBornLoc()));
        }
        ArrayList<Integer> path = ghost.getDeathPath();
        if (path == null || path.isEmpty()) {
            ;
        } else {
            ghost.setDir(path.get(0));
            ghost.updatePath();
        }
        ghost.nextLocation();
    }
}
