package edu.rice.comp504.model;

import edu.rice.comp504.model.item.AItem;
import org.junit.Test;

import java.beans.PropertyChangeListener;

import static org.junit.Assert.*;

public class ResponseBodyTest {
    private PropertyChangeListener[] dynamics;
    private AItem[] eaten;
    private int score;
    private boolean fruitAppear;
    private int lives;
    ResponseBody test = new ResponseBody(null, null, 1, true, 3);

    @Test
    public void setDynamics() {
        test.setDynamics(null);
        assertEquals(test.getDynamics(), null);

    }

    @Test
    public void setEaten() {
        test.setEaten(null);
        assertEquals(test.getEaten(), null);
    }

    @Test
    public void setScore() {
        test.setScore(40);
        assertEquals(test.getScore(), 40);

    }

    @Test
    public void setFruitAppear() {
        test.setFruitAppear(true);
        assertEquals(test.isFruitAppear(), true);
    }



    @Test
    public void setLives() {
        test.setLives(10);
        assertEquals(test.getLives(), 10);
    }
}