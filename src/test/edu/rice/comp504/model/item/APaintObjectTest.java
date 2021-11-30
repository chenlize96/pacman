package edu.rice.comp504.model.item;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class APaintObjectTest {
    APaintObject test = new AItem("fruit", new Point(1,1));
    @Test
    public void getLoc() {
        assertEquals(test.getLoc(), new Point(1,1));
    }

    @Test
    public void setLoc() {
        test.setLoc(new Point(2,2));
        assertEquals(test.getLoc(), new Point(2,2));
        APaintObject test2 = new AItem("fruit", new Point(1,1));
    }

    @Test
    public void getType() {
        assertEquals(test.getType(), "fruit");
    }
}