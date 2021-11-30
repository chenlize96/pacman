package edu.rice.comp504.model;

import edu.rice.comp504.model.item.AItem;

import java.beans.PropertyChangeListener;

public class ResponseBody {

    private PropertyChangeListener[] dynamics;
    private AItem[] eaten;
    private int score;
    private boolean fruitAppear;
    private int lives;
    private boolean eatenAll;

    public ResponseBody(PropertyChangeListener[] dynamics, AItem[] eaten, int score,
                        boolean fruitAppear, int lives, boolean eatenAll) {
        this.dynamics = dynamics;
        this.eaten = eaten;
        this.score = score;
        this.fruitAppear = fruitAppear;
        this.lives = lives;
        this.eatenAll = eatenAll;
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

    public boolean isFruitAppear() {
        return fruitAppear;
    }

    public void setFruitAppear(boolean fruitAppear) {
        this.fruitAppear = fruitAppear;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
