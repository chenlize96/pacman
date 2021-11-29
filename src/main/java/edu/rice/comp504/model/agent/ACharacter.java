package edu.rice.comp504.model.agent;

import edu.rice.comp504.model.PacmanStore;
import edu.rice.comp504.model.item.APaintObject;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public abstract class ACharacter extends APaintObject implements PropertyChangeListener {
    private int currDir; // 0 is top, 1 is right, 2 is down, 3 is left, -1 is not moving
    private Point bornLoc;   //the born location for the character
    //private Point loc; // the current character's location
    //private int timeNum;  //0-5 for ghost, 0-3 for pacman
    //private boolean isAlive;   //true for the character is alive, false for the character is dead
    //private int stepLength;   //the num of pixel moving for each timestamp
    //private IUpdateStrategy movingStrategy;    //the strategy of moving
    //private String type;   //the type of the character
    //private Point mapLoc; //the location for the character based on pixel
    //private int size;   //the size of the character

    /**
     * Constructor for a character.
     * @param type the type of character
     * @param loc the location of character
     * @param bornLoc the born location of character
     * @param currDir the current direction of character
     */
    public ACharacter(String type, Point loc, Point bornLoc, int currDir) {
        super(type, loc);
        //this.size = 15;
        this.bornLoc = bornLoc; // it is used after death
        this.currDir = currDir;
        //this.timeNum = timeNum;
        //this.isAlive = isAlive;
        //this.stepLength = stepLength;
        //this.movingStrategy = movingStrategy;
        //this.type = type;
    }

    /**
     * Get the born location.
     * @return The ball location.
     */
    public Point getBornLoc() {
        return bornLoc;
    }

    /**
     * Get the current direction.
     * @return the current direction
     */
    public int getDir() {
        return currDir;
    }

    /**
     * Set the current direction.
     * @param currDir the new direction
     */
    public void setDir(int currDir) {
        this.currDir = currDir;
    }

    /**
     * Compute the next location of the paint in the paint world given the velocity.
     */
    public void nextLocation() {

    }

    /**
     * Detects collision between a ACharacter and a wall.
     * @return true if there exists a collision, otherwise false
     */
    public boolean detectCollisionWithWalls(APaintObject[][] allMap) {
        Point temp = getLoc();
        // 0 is top, 1 is right, 2 is down, 3 is left, -1 is not moving
        int tempX = temp.x;
        int tempY = temp.y;
        switch (getDir()) {
            case 0:
                if (allMap[tempX - 1][tempY].getType().equals("wall")) {
                    return true;
                }
                break;
            case 1:
                if (this.getType().equals("pacman") && tempY + 1 > PacmanStore.getGrid()[0].length - 1) {   //add
                    setLoc(new Point(tempX, 0));
                    return false;
                }
                if (allMap[tempX][tempY + 1].getType().equals("wall")) {
                    return true;
                }
                break;
            case 2:
                if (allMap[tempX + 1][tempY].getType().equals("wall")) {
                    return true;
                }
                break;
            case 3:
                if (this.getType().equals("pacman") && tempY - 1 < 0) {   //add
                    setLoc(new Point(tempX, PacmanStore.getGrid()[0].length - 1));
                    return false;
                }
                if (this.getType().equals("ghost") && tempY - 1 < 0) {
                    setLoc(new Point(tempX, 0));
                    this.setDir(1);
                }
                if (allMap[tempX][tempY - 1].getType().equals("wall")) {
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    /**
     *  Character responds to the property change support indicating that property has changed.
     */
    public void propertyChange(PropertyChangeEvent e) {
        //System.out.println("character");
    }

}