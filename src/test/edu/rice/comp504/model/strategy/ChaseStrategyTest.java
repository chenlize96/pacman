package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.agent.ACharacter;
import edu.rice.comp504.model.agent.Ghost;
import edu.rice.comp504.model.agent.Pacman;
import junit.framework.TestCase;

import java.util.ArrayList;

public class ChaseStrategyTest extends TestCase {

    public void testMake() {
        IUpdateStrategy test = ChaseStrategy.make();
    }

    public void testTestGetName() {
        IUpdateStrategy test = ChaseStrategy.make();
        assertEquals(test.getName(), "chase");
    }


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
    public void testUpdateState() {
        IUpdateStrategy test = ChaseStrategy.make();
        assertEquals(test.getName(), "chase");
    }
}