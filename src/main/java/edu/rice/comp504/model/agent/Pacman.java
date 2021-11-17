package edu.rice.comp504.model.agent;


import edu.rice.comp504.model.cmd.UpdateStateCmd;

import java.awt.*;
import java.beans.PropertyChangeEvent;


/**
 * The pacman that will be drawn in pacman world.
 */
public class Pacman extends ACharacter{


    private int score;
    private int lives;

    /**
     * The constructor of pacman.
     * @param type the type of pacman
     * @param loc the location of pacman
     * @param bornLoc the born location of pacman
     * @param currDir the current direction of pacman
     */
    public Pacman(String type, Point loc, Point bornLoc, int currDir) {
        super(type, loc, bornLoc, currDir);
        this.score = 0;
        this.lives = 3;
    }

    /**
     * Get the score.
     * @return score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Get the lives.
     * @return lives
     */
    public int getLives() {
        return this.lives;
    }

    /**
     * Set the score.
     * @param score the current score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Set the lives.
     * @param lives the current lives
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     *  Pacman responds to the property change support indicating that property has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //System.out.println("pacman");
        ((UpdateStateCmd) evt.getNewValue()).execute(this);
    }

    /**
     * Compute the next location of the paint in the paint world given the velocity.
     */
    public void nextLocation() {
        Point temp = getLoc();
        int tempX = temp.x;
        int tempY = temp.y;
        switch (getDir()) {
            case 0:
                setLoc(new Point(tempX - 1, tempY));
                break;
            case 1:
                setLoc(new Point(tempX, tempY + 1));
                break;
            case 2:
                setLoc(new Point(tempX + 1, tempY));
                break;
            case 3:
                setLoc(new Point(tempX, tempY - 1));
                break;
            default:
                break;
        }
    }
}
