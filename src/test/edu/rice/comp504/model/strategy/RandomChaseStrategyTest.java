package edu.rice.comp504.model.strategy;

import junit.framework.TestCase;

public class RandomChaseStrategyTest extends TestCase {

    public void testMake() {
        IUpdateStrategy test = RandomChaseStrategy.make();
    }

    public void testTestGetName() {
        IUpdateStrategy test = RandomChaseStrategy.make();
        assertEquals(test.getName(), "random");
    }

    public void testAvailableDirHelper() {
    }

    public void testUpdateState() {
    }
}