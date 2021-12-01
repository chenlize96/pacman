package edu.rice.comp504.model;

import edu.rice.comp504.model.item.AItem;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PacmanStoreTest {
    String hard = "W W W W W W W W W W W W W W W W W W W W W W W W W W W W\n" +
            "W L D E E E D D D D W D D D W W D D D W D D D E D E L W\n" +
            "W D W W W D W W W D D D W D D W D b D D D b b D W W D W\n" +
            "T D W E W D W E W W D W W W D D D b b D b b b D E E E T\n" +
            "W D W E E D W E E W D W D W W W D b b b b b b D W W W W\n" +
            "W D W W W D W E E W D W D W W W W E b b E b b D W E D W\n" +
            "W D D D D D W E E W D W D W W E E B B b b b b E W W D W\n" +
            "W W W W W D W E E W D D D D D B B B b b b b b D W W D W\n" +
            "W D D E D D W E W W D W W W D B b B b b b b b D D D D W\n" +
            "W D W W W D W W W D D W E E D B b b B b b b b D W W D W\n" +
            "W D W D D D D D D D W W W W D D D E b b b b b D W D D W\n" +
            "W D W D W W W W W D W 1 2 W D W W D E b b b D D W E W W\n" +
            "W D W D E E E E D D W 3 4 W D W W W E D D E D W W D W W\n" +
            "W D W W W D W W W D W E W W D W W W W W W D W W D D W W\n" +
            "W D D D D D D D W D E E W D D D D D D W W D D D D W W W\n" +
            "W D b b B D B D W D W W W D W W W W D D D D W W W W D W\n" +
            "W D b B B D B D D D D D D D W W W W D W W D W W D D D W\n" +
            "W D D D b b b b b W W W W D W D D D D D D D D D D W D W\n" +
            "W W W D b E b b b b b D D D W D W W D R R D R R D W D W\n" +
            "W W W D b b b b B B B D W D W D W W D R R R R R D W D W\n" +
            "W D D D b b b b B E E E W D W D E W D R D P D R D W D W\n" +
            "W W D b B b b b b b E W W D W W W W D R R D R D D W D W\n" +
            "T E D b B B B B S D D W W D W W W D D D R D D D W W D T\n" +
            "W W L D D D D D D W W W W D D D D D W D R R D W W D D W\n" +
            "W W W W W W D W W W W W W D W W D W W D D E D W W W L W\n" +
            "W W W W W W W W W W W W W W W W W W W W W W W W W W W W";
    private PacmanStore pacmanStore;
    private String[][] grid;
    private int row;
    private int col;

    public String[][] initializeLevel(String level, int ghostNum, String fruitType) {
        grid = LevelStore.getTheLevel(level);
        return grid;
    }
     PacmanStore test = new PacmanStore();
    @Test
    public void initialize() {
       test.initialize(initializeLevel(hard, 4, "apple"), 4, "apple");
    }

    @Test
    public void getGrid() {
       PacmanStore.getGrid();

    }

    @Test
    public void getOnlyStratFac() {
        PacmanStore.getOnlyStratFac();
    }

    @Test
    public void addEatenItems() {
        PacmanStore.addEatenItems(new AItem("fruit", new Point(1,1)));
    }

    @Test
    public void getCanvasDims() {
        PacmanStore.getCanvasDims();
    }

    @Test
    public void setCanvasDims() {
        test.initialize(initializeLevel(hard, 4, "apple"), 4, "apple");
        test.setCanvasDims(new Point(1,1));
        assertEquals(PacmanStore.getCanvasDims(), new Point(1,1));
    }

    @Test
    public void isFruitAppear() {
        test.initialize(initializeLevel(hard, 4, "apple"), 4, "apple");
        assertEquals(PacmanStore.isFruitAppear(), true);

    }

    @Test
    public void setFruitAppear() {
        PacmanStore.setFruitAppear(true);
        assertEquals(PacmanStore.isFruitAppear(), true);
    }

    @Test
    public void getScore() {
        assertEquals(PacmanStore.getScore(), 0);
    }

    @Test
    public void setScore() {
        PacmanStore.setScore(1);
        assertEquals(PacmanStore.getScore(), 1);
    }

    @Test
    public void getEatenItems() {
        assertEquals(PacmanStore.getEatenItems(), new ArrayList<>());
    }

    @Test
    public void getLastFruitAppearTime() {
        test.initialize(initializeLevel(hard, 4, "apple"), 4, "apple");
        test.setLastFruitAppearTime(1);
        assertEquals(test.getLastFruitAppearTime(), 1);
    }

    @Test
    public void setLastFruitAppearTime() {
        test.initialize(initializeLevel(hard, 4, "apple"), 4, "apple");
        test.setLastFruitAppearTime(1);
        assertEquals(test.getLastFruitAppearTime(), 1);
    }
    @Test
    public void setCurrentFrame() {
        PacmanStore.setCurrentFrame(1);
        assertEquals(PacmanStore.getCurrentFrame(), 1);
    }
    @Test
    public void getCurrentFrame() {
        assertEquals(PacmanStore.getCurrentFrame(), -1);
    }

    @Test
    public void setNumEatenGhost() {
        PacmanStore.setNumEatenGhost(1);
        assertEquals(PacmanStore.getNumEatenGhost(), 1);
    }

    @Test
    public void getNumEatenGhost() {
        assertEquals(PacmanStore.getNumEatenGhost(), 0);
    }

    @Test
    public void getDarkBlueFrames() {
        assertEquals(PacmanStore.getDarkBlueFrames(), 100);
    }

    @Test
    public void getBlinkFrames() {
        assertEquals(PacmanStore.getBlinkFrames(), 50);
    }

    @Test
    public void getGhostScoreList() {
        assertEquals(PacmanStore.getBlinkFrames(), 50);
    }

    @Test
    public void getLives() {
        test.initialize(initializeLevel(hard, 4, "apple"), 4, "apple");
        PacmanStore.getLives();
    }
    @Test
    public void setLive() {
        test.initialize(initializeLevel(hard, 4, "apple"), 4, "apple");
        test.setLive(2);
        test.removeCharactersFromStore();
        PacmanStore.getGhostScoreList();
        PacmanStore.setPacmanDir(1);
    }
    @Test
    public void updatePacmanWorld() {
        PacmanStore.updatePacmanWorld();
    }

    @Test
    public void getListeners() {
    }

    @Test
    public void switchStrategy() {
    }

    @Test
    public void setPacmanDir() {
    }

    @Test
    public void addCharacterToStore() {
    }

    @Test
    public void removeCharactersFromStore() {
    }

    @Test
    public void removeTheGhost() {
    }

}