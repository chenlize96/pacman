package edu.rice.comp504.model.item;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class AItemTest {
    AItem test = new AItem("fruit", new Point(1,1));
    @Test
    public void propertyChange() {
    }

    @Test
    public void getScore() {
        assertEquals(test.getScore(), 0);
        AItem test2 = new AItem("fruit", new Point(1,1));
    }

    @Test
    public void setScore() {
        test.setScore(1);
        assertEquals(test.getScore(), 1);
    }

    @Test
    public void isEaten() {
        assertEquals(test.isEaten(), false);
    }

    @Test
    public void setEaten() {
        test.setEaten(true);
        assertEquals(test.isEaten(), true);
    }
}