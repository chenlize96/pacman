package edu.rice.comp504.model.item;

import java.awt.*;

public class EmptyCell extends APaintObject {

    /**
     * Constructor.
     *
     * @param loc  The location of the obj on the canvas
     */
    public EmptyCell(Point loc) {
        super("empty", loc);
    }
}
