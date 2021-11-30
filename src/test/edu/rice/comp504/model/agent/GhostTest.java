package edu.rice.comp504.model.agent;

import edu.rice.comp504.model.cmd.UpdateStateCmd;
import edu.rice.comp504.model.strategy.GhostStrategyFac;
import org.junit.Test;

import java.awt.*;
import java.beans.PropertyChangeEvent;

import static org.junit.Assert.*;

public class GhostTest {
    Ghost test = new Ghost("ghost", new Point(1,1), new Point(1,1), 0);
    @Test
    public void setName() {
        test.setName("test");
        assertEquals(test.getName(), "test");
        Ghost test2 = new Ghost("ghost", new Point(1,1), new Point(1,1), 0);
    }

    @Test
    public void getName() {
        assertEquals(test.getName(), null);
    }

    @Test
    public void makeGhost() {
    }

    @Test
    public void setStrategy() {
        test.setStrategy(GhostStrategyFac.makeStrategyFactory().make("avoid"));
        assertEquals(test.getStrategy(), GhostStrategyFac.makeStrategyFactory().make("avoid"));
    }

    @Test
    public void getStrategy() {
        assertEquals(test.getStrategy(), GhostStrategyFac.makeStrategyFactory().make(""));
    }
    @Test
    public void nextLocation() {
        test.nextLocation();
        test.setDir(1);
        test.nextLocation();
        test.setDir(2);
        test.nextLocation();
        test.setDir(3);
        test.nextLocation();
        test.setDir(4);
        test.nextLocation();
    }

    @Test
    public void propertyChange() {

    }

    @Test
    public void getGhostStatus() {
        assertEquals(test.getGhostStatus(), "normal");
    }

    @Test
    public void setGhostStatus() {
        test.setGhostStatus("dead");
        assertEquals(test.getGhostStatus(), "dead");
    }
}