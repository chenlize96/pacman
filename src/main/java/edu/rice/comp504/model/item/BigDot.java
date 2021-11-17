package edu.rice.comp504.model.item;

import java.awt.*;

public class BigDot extends AItem{

    /**
     * Constructor.
     *
     * @param loc   The location of the obj on the canvas
     */
    public BigDot(Point loc) {
        super("bigDot", loc);
        this.score = 20;
    }
}
