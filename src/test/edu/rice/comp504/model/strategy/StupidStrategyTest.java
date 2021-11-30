package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.PacmanStore;
import edu.rice.comp504.model.agent.Ghost;
import edu.rice.comp504.model.agent.Pacman;
import junit.framework.TestCase;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class StupidStrategyTest extends TestCase {

    public void testMake() {
        IUpdateStrategy test = StupidStrategy.make();
    }

    public void testTestGetName() {
        IUpdateStrategy test = StupidStrategy.make();
        assertEquals(test.getName(), "stupid");

    }

    public void testUpdateState() {
        IUpdateStrategy test = StupidStrategy.make();
        Ghost context = new Ghost("ghost", new Point(12, 13), new Point(12, 13), 0);
        Pacman context2 = new Pacman("pacman", new Point(PacmanStore.getLetterGrid().length - 2, 15), new Point(PacmanStore.getLetterGrid().length - 2, 15), 0);
        test.updateState(context, context2);
        Ghost context3 = new Ghost("ghost", new Point(1, 1), new Point(1, 1), 0);
        Pacman context4 = new Pacman("pacman", new Point(0, 0), new Point(0, 0), 0);
        test.updateState(context3, context4);
        test.updateState(context3, context4);
    }
}