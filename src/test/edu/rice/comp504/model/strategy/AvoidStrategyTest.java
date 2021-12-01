package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.PacmanStore;
import edu.rice.comp504.model.agent.ACharacter;
import edu.rice.comp504.model.agent.Ghost;
import edu.rice.comp504.model.agent.Pacman;
import junit.framework.TestCase;

import java.awt.*;


public class AvoidStrategyTest extends TestCase{
    public void testMake() {
        IUpdateStrategy test = AvoidStrategy.make();
    }

    public void testTestGetName() {
        IUpdateStrategy test = AvoidStrategy.make();
        assertEquals(test.getName(), "avoid");

    }

    public void testAvailableDirHelper() {
        IUpdateStrategy test = AvoidStrategy.make();
        ((AvoidStrategy)test).availableDirHelper(new Point(1,1));
        ((AvoidStrategy)test).availableDirHelper(new Point(2,1));
        ((AvoidStrategy)test).availableDirHelper(new Point(3,1));
        ((AvoidStrategy)test).availableDirHelper(new Point(4,1));
        ((AvoidStrategy)test).availableDirHelper(new Point(5,1));
        ((AvoidStrategy)test).availableDirHelper(new Point(6,1));
        ((AvoidStrategy)test).availableDirHelper(new Point(7,1));
        ((AvoidStrategy)test).availableDirHelper(new Point(8,1));
    }

    public void testRandomAvailDir() {
        IUpdateStrategy test = AvoidStrategy.make();
        int[] dir = ((AvoidStrategy)test).availableDirHelper(new Point(1,1));
        Pacman context2 = new Pacman("pacman", new Point(PacmanStore.getLetterGrid().length - 2, 15), new Point(PacmanStore.getLetterGrid().length - 2, 15), 0);
        Ghost context = new Ghost("ghost", new Point(12, 13), new Point(12, 13), 0);
        ((AvoidStrategy)test).randomAvailDir(new int[]{1, 1, 1, 1}, context2, context);
        ((AvoidStrategy)test).randomAvailDir(new int[]{0, 1, 1, 1}, context2, context);
        ((AvoidStrategy)test).randomAvailDir(new int[]{0, 0, 1, 1}, context2, context);
        ((AvoidStrategy)test).randomAvailDir(new int[]{0, 0, 0, 1}, context2, context);

    }


    public void testTmpHelper() {
        IUpdateStrategy test = AvoidStrategy.make();
        Pacman context2 = new Pacman("pacman", new Point(1,1), new Point(1,1), 0);
        Ghost context = new Ghost("ghost", new Point(12, 13), new Point(12, 13), 0);
        ((AvoidStrategy)test).tmpHelper(context, context2);
        context2 = new Pacman("pacman", new Point(2,1), new Point(2,1), 0);
        context = new Ghost("ghost", new Point(12, 13), new Point(12, 13), 0);
        ((AvoidStrategy)test).tmpHelper(context, context2);
        context2 = new Pacman("pacman", new Point(3,1), new Point(3,1), 0);
        context = new Ghost("ghost", new Point(13, 13), new Point(12, 13), 0);
        ((AvoidStrategy)test).tmpHelper(context, context2);
        context2 = new Pacman("pacman", new Point(4,1), new Point(4,1), 0);
        context = new Ghost("ghost", new Point(14, 13), new Point(12, 13), 0);
        ((AvoidStrategy)test).tmpHelper(context, context2);
        context2 = new Pacman("pacman", new Point(5,1), new Point(5,1), 0);
        context = new Ghost("ghost", new Point(15, 13), new Point(12, 13), 0);
        ((AvoidStrategy)test).tmpHelper(context, context2);
        context2 = new Pacman("pacman", new Point(6,1), new Point(6,1), 0);
        context = new Ghost("ghost", new Point(16, 13), new Point(12, 13), 0);
        ((AvoidStrategy)test).tmpHelper(context, context2);
        context2 = new Pacman("pacman", new Point(7,1), new Point(7,1), 0);
        context = new Ghost("ghost", new Point(17, 13), new Point(12, 13), 0);
        ((AvoidStrategy)test).tmpHelper(context, context2);
        context2 = new Pacman("pacman", new Point(8,1), new Point(8,1), 0);
        context = new Ghost("ghost", new Point(18, 13), new Point(12, 13), 0);
        ((AvoidStrategy)test).tmpHelper(context, context2);
    }

    public void testUpdateState() {
        IUpdateStrategy test = AvoidStrategy.make();
        Ghost context = new Ghost("ghost", new Point(12, 13), new Point(12, 13), 0);
        Pacman context2 = new Pacman("pacman", new Point(PacmanStore.getLetterGrid().length - 2, 15), new Point(PacmanStore.getLetterGrid().length - 2, 15), 0);
        test.updateState(context, context2);
        test.updateState(context, context2);


        /*Ghost context3 = new Ghost("ghost", new Point(1, 1), new Point(1, 1), 0);
        Pacman context4 = new Pacman("pacman", new Point(0, 0), new Point(0, 0), 0);
        test.updateState(context3, context4);*/


    }
}

