package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.agent.Pacman;
import junit.framework.TestCase;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class AmbushStrategyTest extends TestCase {

    public void testMake() {
        IUpdateStrategy test = AmbushStrategy.make();
    }

    public void testTestGetName() {
        IUpdateStrategy test = AmbushStrategy.make();
        assertEquals(test.getName(), "ambush");

    }

    public void testFixedLoc() {
        IUpdateStrategy test = AmbushStrategy.make();
        Pacman test2 = new Pacman("pacman", new Point(1,1), new Point(1,1), 0);
        Point test3 = ((AmbushStrategy)test).fixedMarginLoc(test2);
        test2 = new Pacman("pacman", new Point(1,1), new Point(1,1), 3);
        test3 = ((AmbushStrategy)test).fixedMarginLoc(test2);
//        test2 = new Pacman("pacman", new Point(5,5), new Point(6,6), 2);
//        test3 = ((AmbushStrategy)test).fixedMarginLoc(test2);
//        test2 = new Pacman("pacman", new Point(5,5), new Point(6,6), 1);
//        test3 = ((AmbushStrategy)test).fixedMarginLoc(test2);
    }

    public void testUpdateState() {
    }
}