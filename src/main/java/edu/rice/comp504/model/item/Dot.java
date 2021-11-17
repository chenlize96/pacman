package edu.rice.comp504.model.item;

import java.awt.*;

public class Dot extends AItem{

    /**
     * Constructor.
     *
     * @param loc   The location of the obj on the canvas
     */
    public Dot(Point loc) {
        super("dot", loc);
        this.score = 10;
    }
}
