package edu.rice.comp504.model.item;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class FruitTest {

    @Test
    public void getTimeLeft() {
    Fruit test = new Fruit(new Point(1,1), 1, "apple");
        assertEquals(test.getTimeLeft(), 1);
    }

    @Test
    public void setTimeLeft() {
        Fruit test = new Fruit(new Point(1,1), 1, "apple");
        test.setTimeLeft(2);
        assertEquals(test.getTimeLeft(), 2);
    }
}