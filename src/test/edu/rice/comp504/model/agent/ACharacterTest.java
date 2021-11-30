package edu.rice.comp504.model.agent;

import edu.rice.comp504.model.PacmanStore;
import edu.rice.comp504.model.item.APaintObject;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class ACharacterTest {
    ACharacter test = new Ghost("ghost", new Point(1,1), new Point(1,1), 0);
    public void testGetBornLoc() {
        Point testPot = test.getBornLoc();
        assertEquals(testPot, new Point(1,1));
    }

    @Test
    public void getDir() {
        int testDir = test.getDir();
        assertEquals(testDir, 0);
    }

    @Test
    public void setDir() {
        test.setDir(2);
        assertEquals(test.getDir(), 2);
    }

    @Test
    public void nextLocation() {
        test.nextLocation();
    }

    @Test
    public void detectCollisionWithWalls() {

    }

    @Test
    public void propertyChange() {
    }
}