package edu.rice.comp504.model.item;

import java.awt.*;

public class Wall extends APaintObject {

    /**
     * Constructor.
     *
     * @param loc  The location of the obj on the canvas
     */
    public Wall(Point loc) {
        super("wall", loc);
    }

}
