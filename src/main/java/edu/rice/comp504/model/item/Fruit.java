package edu.rice.comp504.model.item;

import java.awt.*;

public class Fruit extends AItem{

    private int timeLeft;

    /**
     * Constructor.
     *
     * @param loc   The location of the obj on the canvas
     * @param timeLeft The time left
     */
    public Fruit(Point loc, int timeLeft) {
        super("fruit", loc);
        this.timeLeft = timeLeft;
        this.score = 50;
    }

    /**
     * get the timeLeft.
     * @return timeLeft.
     */
    public int getTimeLeft() {
        return timeLeft;
    }

    /**
     * set a new timeLeft.
     * @param timeLeft timeLeft.
     */
    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

}
