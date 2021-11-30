package edu.rice.comp504.model.agent;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class PacmanTest {
    Pacman test = new Pacman("pacman", new Point(1,1), new Point(1,1), 0);

    @Test
    public void getScore() {
        assertEquals(test.getScore(), 0);
    }

    @Test
    public void getLives() {
        assertEquals(test.getLives(), 3);
        Pacman test2 = new Pacman("pacman", new Point(1,1), new Point(1,1), 0);
    }

    @Test
    public void setScore() {
        test.setScore(4);
        assertEquals(test.getScore(), 4);
    }

    @Test
    public void setLives() {
        test.setLives(4);
        assertEquals(test.getLives(), 4);
    }

    @Test
    public void propertyChange() {
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

}