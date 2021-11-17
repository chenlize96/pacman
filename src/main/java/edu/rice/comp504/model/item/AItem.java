package edu.rice.comp504.model.item;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AItem extends APaintObject implements PropertyChangeListener {

    public int score;
    private boolean eaten;

    /**
     * Constructor.
     *
     * @param type The name of the obj
     * @param loc  The location of the obj on the canvas
     */
    public AItem(String type, Point loc) {
        super(type, loc);
        this.score = 0;
        this.eaten = false;
    }

    /**
     * Property event change.
     * @param evt event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    /**
     * get the score of the item.
     * @return score.
     */
    public int getScore() {
        return score;
    }

    /**
     * set a new score.
     * @param score the score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * return boolean, if the item eaten.
     * @return eaten.
     */
    public boolean isEaten() {
        return eaten;
    }

    /**
     * set a new status for eaten.
     * @param eaten eaten.
     */
    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }
}
