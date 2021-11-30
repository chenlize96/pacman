package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.agent.ACharacter;
import edu.rice.comp504.model.agent.Ghost;
import edu.rice.comp504.model.agent.Pacman;
import junit.framework.TestCase;

import java.awt.*;
import java.util.ArrayList;

public class ChaseStrategyTest extends TestCase {

    public void testMake() {
        IUpdateStrategy test = ChaseStrategy.make();
    }

    public void testTestGetName() {
        IUpdateStrategy test = ChaseStrategy.make();
        assertEquals(test.getName(), "chase");
    }

    public void testUpdateState() {
        IUpdateStrategy test = ChaseStrategy.make();
        assertEquals(test.getName(), "chase");
        Ghost test2 = new Ghost("ghost", new Point(1,1), new Point(1,1), 0);
        Pacman test3 = new Pacman("pacman", new Point(1,1), new Point(1,1), 0);
        test.updateState(test2,test3);
    }
}