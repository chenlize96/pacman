package edu.rice.comp504.model.agent;

import edu.rice.comp504.model.cmd.IPaintObjCmd;
import edu.rice.comp504.model.strategy.GhostStrategyFac;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.beans.PropertyChangeEvent;

/**
 * The ghost that will be drawn in pacman world.
 */
public class Ghost extends ACharacter {

    private IUpdateStrategy movingStrategy;    //the strategy of moving
    private String name;

    public Ghost(String type, Point loc, Point bornLoc, int currDir) {
        super(type, loc, bornLoc, currDir);
        movingStrategy = GhostStrategyFac.makeStrategyFactory().make("");
    }

    /**
     * Set the name of ghost.
     * @param name the name of the ghost
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the name of ghost.
     * @param name the name of the ghost
     */
    public String getName() { //add
        return this.name; //add
    }   //add


    /**
     * Make a ghost.
     * @param strategy a strategy
     *
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

}
