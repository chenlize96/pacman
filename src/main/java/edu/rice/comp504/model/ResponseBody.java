package edu.rice.comp504.model;

import edu.rice.comp504.model.item.AItem;

import java.beans.PropertyChangeListener;

public class ResponseBody {

    private PropertyChangeListener[] dynamics;
    private AItem[] eaten;
    private int score;

    public ResponseBody(PropertyChangeListener[] dynamics, AItem[] eaten, int score) {
        this.dynamics = dynamics;
        this.eaten = eaten;
        this.score = score;
    }

    public PropertyChangeListener[] getDynamics() {
        return dynamics;
    }

    public void setDynamics(PropertyChangeListener[] dynamics) {
        this.dynamics = dynamics;
    }

    public AItem[] getEaten() {
        return eaten;
    }

    public void setEaten(AItem[] eaten) {
        this.eaten = eaten;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
