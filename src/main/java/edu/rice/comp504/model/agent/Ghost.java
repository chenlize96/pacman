package edu.rice.comp504.model.agent;

import edu.rice.comp504.model.cmd.IPaintObjCmd;
import edu.rice.comp504.model.strategy.GhostStrategyFac;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

/**
 * The ghost that will be drawn in pacman world.
 */
public class Ghost extends ACharacter {

    private IUpdateStrategy movingStrategy;    //the strategy of moving
    private String name;
    private String ghostStatus; // "normal", "vulnerable_dark_blue", "vulnerable_blink", "dead"
    private boolean fresh;
    private ArrayList<Integer> path = new ArrayList<>();
    private ArrayList<Integer> deathPath = new ArrayList<>();


    public Ghost(String type, Point loc, Point bornLoc, int currDir) {
        super(type, loc, bornLoc, currDir);
        this.fresh = true; // used to fresh a star algorithm periodically
        movingStrategy = GhostStrategyFac.makeStrategyFactory().make("");
        ghostStatus = "normal";
    }

    /**
     * Set the death path of ghost.
     */
    public void setDeathPath(ArrayList<Integer> path) {
        this.deathPath = path;
    }

    /**
     * Get the death path of ghost.
     */
    public ArrayList<Integer> getDeathPath() {
        return deathPath;
    }

    /**
     * Set the path of ghost.
     */
    public void setPath(ArrayList<Integer> path) {
        this.path = path;
    }

    /**
     * Get the path of ghost.
     */
    public ArrayList<Integer> getPath() {
        return path;
    }

    /**
     * Remove the first element of the path.
     */
    public void updatePath(String pathName) {
        if (pathName.equals("path")) {
            path.remove(0);
        } else {
            deathPath.remove(0);
        }
    }

    /**
     * Set the fresh of ghost.
     */
    public void setFresh() {
        this.fresh = false;
    }

    /**
     * Get the fresh of ghost.
     */
    public boolean getFresh() {
        return fresh;
    }

    /**
     * Set the name of ghost.
     * @param name the name of the ghost
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the name of ghost.
     */
    public String getName() { //add
        return this.name; //add
    }   //add

    /**
     * Make a ghost.
     * @param strategy a strategy
     */
    public static Ghost makeGhost(String strategy)  {
        return null;
    }

    /**
     * the strategy of moving.
     * @return the strategy of moving
     */
    public IUpdateStrategy getStrategy() {
        return movingStrategy;
    }

    /**
     * Set the moving strategy of the character.
     * @return the strategy of moving
     */
    public void setStrategy(IUpdateStrategy newMovingStrategy) {
        this.movingStrategy = newMovingStrategy;
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

    /**
     * Update state of the ghost when the property change event occurs by executing the command.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ((IPaintObjCmd) evt.getNewValue()).execute(this);
    }

    /**
     * get ghost status.
     * @return ghost status.
     */
    public String getGhostStatus() {
        return ghostStatus;
    }

    /**
     * set ghost status.
     * @param ghostStatus ghost status.
     */
    public void setGhostStatus(String ghostStatus) {
        this.ghostStatus = ghostStatus;
    }
}
