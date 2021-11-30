package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.agent.Ghost;
import edu.rice.comp504.model.agent.Pacman;
import junit.framework.TestCase;

import java.awt.*;

public class RandomChaseStrategyTest extends TestCase {

    public void testMake() {
        IUpdateStrategy test = RandomChaseStrategy.make();
    }

    public void testTestGetName() {
        IUpdateStrategy test = RandomChaseStrategy.make();
        assertEquals(test.getName(), "random");
    }

    public void testAvailableDirHelper() {
        IUpdateStrategy test = RandomChaseStrategy.make();
        ((RandomChaseStrategy)test).availableDirHelper(new Point(1,1));
        ((RandomChaseStrategy)test).availableDirHelper(new Point(2,1));
        ((RandomChaseStrategy)test).availableDirHelper(new Point(3,1));
        ((RandomChaseStrategy)test).availableDirHelper(new Point(4,1));
        ((RandomChaseStrategy)test).availableDirHelper(new Point(5,1));
        ((RandomChaseStrategy)test).availableDirHelper(new Point(6,1));
        ((RandomChaseStrategy)test).availableDirHelper(new Point(7,1));
        ((RandomChaseStrategy)test).availableDirHelper(new Point(8,1));
    }

    public void testUpdateState() {
        IUpdateStrategy test = RandomChaseStrategy.make();
        Ghost test2 = new Ghost("ghost", new Point(1,1), new Point(1,1), 0);
        Pacman test3 = new Pacman("pacman", new Point(2,1), new Point(1,1), 0);
        ((RandomChaseStrategy)test).updateState(test2, test3);
        test2 = new Ghost("ghost", new Point(3,1), new Point(1,1), 0);
        ((RandomChaseStrategy)test).updateState(test2, test3);
    }
}