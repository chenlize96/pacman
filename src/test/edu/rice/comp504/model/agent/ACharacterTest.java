package edu.rice.comp504.model.agent;

import edu.rice.comp504.model.PacmanStore;
import edu.rice.comp504.model.item.APaintObject;
import edu.rice.comp504.model.item.EmptyCell;
import edu.rice.comp504.model.item.Wall;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class ACharacterTest {
    ACharacter test = new Ghost("ghost", new Point(1,1), new Point(1,1), 0);

    @Test
    public void testGetBornLoc() {
        Point testPot = test.getBornLoc();
        assertEquals(testPot, new Point(1,1));
        ACharacter test2 = new Ghost("ghost", new Point(1,1), new Point(1,1), 0);
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
        assertEquals(new Point(0,1),test.getLoc());
    }

    @Test
    public void detectCollisionWithWalls() {
        APaintObject[][] map = PacmanStore.getGrid();

        assertEquals(true,test.detectCollisionWithWalls(map));
        test.setDir(3);
        assertEquals(true,test.detectCollisionWithWalls(map));
        test.setDir(1);
        assertEquals(false,test.detectCollisionWithWalls(map));
        test.setDir(2);
        assertEquals(false,test.detectCollisionWithWalls(map));

        //test.setLoc();

        map[0][0] = new EmptyCell(new Point(0,0));
        map[1][0] = new EmptyCell(new Point(1,0));
        map[1][1] = new EmptyCell(new Point(0,0));
        map[0][1] = new Wall(new Point(0,1));
    }

    @Test
    public void propertyChange() {
    }
}