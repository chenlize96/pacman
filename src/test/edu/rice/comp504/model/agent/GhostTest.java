package edu.rice.comp504.model.agent;

import edu.rice.comp504.model.cmd.UpdateStateCmd;
import edu.rice.comp504.model.strategy.GhostStrategyFac;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import org.junit.Test;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

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
        assertEquals(null,Ghost.makeGhost("null"));
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

    @Test
    public void setDeathPath() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        test.setDeathPath(list);
        assertEquals(list,test.getDeathPath());
    }

    @Test
    public void setFresh() {
        test.setFresh();
        assertEquals(false,test.getFresh());
    }

    @Test
    public void resetBornStrategy() {
        IUpdateStrategy strategy = test.getStrategy();
        test.resetBornStrategy();
        assertEquals(strategy,test.getStrategy());
    }

    @Test
    public void updatePath() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        test.setPath(list);
        test.setDeathPath(list);

        test.updatePath("path");
        test.updatePath("death");

        assertEquals(new ArrayList<>(),test.getDeathPath());
        assertEquals(new ArrayList<>(),test.getPath());
    }

}