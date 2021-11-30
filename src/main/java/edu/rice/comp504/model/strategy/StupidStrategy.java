package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.agent.ACharacter;
import edu.rice.comp504.model.agent.Ghost;
import edu.rice.comp504.model.agent.Pacman;

import java.awt.*;
import java.util.ArrayList;

/**
 * Use for orange. Within 8 units, go back to the corner; otherwise use chaser.
 */
public class StupidStrategy implements IUpdateStrategy{
    private static IUpdateStrategy singleton;

    /**
     * Make the only random chase strategy.
     *
     * @return A random chase strategy
     */
    public static IUpdateStrategy make() {
        if (singleton == null) {
            singleton = new StupidStrategy();
        }
        return singleton;
    }

    @Override
    public String getName() {
        return "stupid";
    }


    @Override
    public void updateState(ACharacter context, ACharacter context2) {
        Ghost ghost = (Ghost) context;
        Pacman pacman = (Pacman) context2;
        if (ghost.getFresh() || AStarAlgorithm.ifNode(ghost.getLoc().x, ghost.getLoc().y)) {
            if (AStarAlgorithm.manhattanHeuristic(ghost.getLoc(), pacman.getLoc()) > 8) {
                ghost.setPath(AStarAlgorithm.aStarSearch(ghost.getLoc(), pacman.getLoc()));
            } else {
                ghost.setPath(AStarAlgorithm.aStarSearch(ghost.getLoc(), new Point(1, 1)));
            }
            ghost.setFresh();
        }
        ArrayList<Integer> path = ghost.getPath();
        if (path == null || path.isEmpty()) {
            ;
        } else {
            ghost.setDir(path.get(0));
            ghost.updatePath();
        }
        ghost.nextLocation();
    }
}
