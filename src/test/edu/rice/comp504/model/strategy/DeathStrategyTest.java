package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.agent.Ghost;
import edu.rice.comp504.model.agent.Pacman;
import junit.framework.TestCase;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class DeathStrategyTest extends TestCase {

    public void testMake() {
        IUpdateStrategy test = DeathStrategy.make();
    }

    public void testTestGetName() {
        IUpdateStrategy test = DeathStrategy.make();
        assertEquals(test.getName(), "death");

    }

    public void testUpdateState() {
        IUpdateStrategy test = DeathStrategy.make();
        Ghost context = new Ghost("ghost", new Point(1,1), new Point(1,1), 0);
        test.updateState(context, context);
        test.updateState(context, context);
    }
}