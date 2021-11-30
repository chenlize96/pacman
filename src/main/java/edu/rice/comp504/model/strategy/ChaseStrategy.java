package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.agent.ACharacter;
import edu.rice.comp504.model.agent.Ghost;
import edu.rice.comp504.model.agent.Pacman;

import java.util.ArrayList;

/**
 * Use A*. This is for the red ghost.
 * Use the location of the current pacman and the ghost.
 * Death strategy is the same as this strategy - back to the born strategy
 */
public class ChaseStrategy  implements IUpdateStrategy{

    private static IUpdateStrategy singleton;

    /**
     * Make the only random chase strategy.
     *
     * @return A random chase strategy
     */
    public static IUpdateStrategy make() {
        if (singleton == null) {
            singleton = new ChaseStrategy();
        }
        return singleton;
    }

    /**
     * Return RandomChase type.
     */
    @Override
    public String getName() {
        return "chase";
    }


    @Override
    /**
     * Update a ghost position randomly.
     */
    public void updateState(ACharacter context, ACharacter context2) {
        Ghost ghost = (Ghost) context;
        Pacman pacman = (Pacman) context2;
        if (ghost.getFresh() || AStarAlgorithm.ifNode(ghost.getLoc().x, ghost.getLoc().y)) {
            ghost.setPath(AStarAlgorithm.aStarSearch(ghost.getLoc(), pacman.getLoc()));
            ghost.setFresh();
        }
        ArrayList<Integer> path = ghost.getPath();
        if (path == null || path.isEmpty()) {
            ;
        } else {
            ghost.setDir(path.get(0));
            ghost.updatePath("path");
        }
        ghost.nextLocation();
    }
}
