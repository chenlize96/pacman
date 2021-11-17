package edu.rice.comp504.model.item;

import java.awt.*;

/**
 * The APaintObj will be drawn in the pacman world.
 */
public abstract class APaintObject {
    private Point loc;
    private String type;

    /**
     * Constructor.
     *
     * @param loc   The location of the obj on the canvas
     * @param type  The type of the obj
     */
    public APaintObject(String type, Point loc) {
        this.loc = loc;
        this.type = type;
    }

    /**
     * Get the paint object location in the pacman world.
     * @return The paint object location.
     */
    public Point getLoc() {
        return loc;
    }

    /**
     * Set the paint object location in the canvas.
     * @param loc  The paint object coordinate.
     */
    public void setLoc(Point loc) {
        this.loc = loc;
    }

    /**
     * Get the paint object Type.
     * @return paint object Type
     */
    public String getType() {
        return type;
    }

}