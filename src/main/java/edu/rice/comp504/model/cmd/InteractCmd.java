package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.PacmanStore;
import edu.rice.comp504.model.agent.ACharacter;
import edu.rice.comp504.model.agent.Ghost;
import edu.rice.comp504.model.agent.Pacman;
import edu.rice.comp504.model.item.*;

import java.awt.*;
import java.beans.PropertyChangeListener;

public class InteractCmd implements IPaintObjCmd {

    private final PropertyChangeListener[] iCharacters;

    /**
     * The constructor.
     *
     * @param iCharacters pacman and ghosts
     */
    public InteractCmd(PropertyChangeListener[] iCharacters) {
        this.iCharacters = iCharacters;
    }

    /**
     * Make Pacman to interact with dots and ghosts.
     */
    @Override
    public void execute(ACharacter context) {

        if (context.getType().equals("pacman")) {
            Pacman pacman = (Pacman) context;
            collideWithDots(pacman);
            if (collideWithGhost()!=null) {
                Ghost ghost = collideWithGhost();
                resetCharacter(); //Pacman dies or eat the ghost.
            }
        }

    }

    /**
     * If Pacman collides with a dot.
     */
    private void collideWithDots(Pacman pacman) {
        APaintObject[][] grid = PacmanStore.getGrid();
        Point currLoc = pacman.getLoc();
        String type = grid[currLoc.x][currLoc.y].getType();
        if(type.equals("dot")) {
            PacmanStore.setScore(PacmanStore.getScore() + ((Dot)grid[currLoc.x][currLoc.y]).score);
            PacmanStore.addEatenItems((Dot)grid[currLoc.x][currLoc.y]);
            grid[currLoc.x][currLoc.y] = new EmptyCell(new Point(currLoc.x, currLoc.y));
        } else if(type.equals("bigDot")) {
            PacmanStore.setScore(PacmanStore.getScore() + ((BigDot)grid[currLoc.x][currLoc.y]).score);
            PacmanStore.addEatenItems((BigDot)grid[currLoc.x][currLoc.y]);
            grid[currLoc.x][currLoc.y] = new EmptyCell(new Point(currLoc.x, currLoc.y));
        } else if(type.equals("fruit")) {
            PacmanStore.setScore(PacmanStore.getScore() + ((Fruit)grid[currLoc.x][currLoc.y]).score);
            PacmanStore.addEatenItems((Fruit)grid[currLoc.x][currLoc.y]);
            grid[currLoc.x][currLoc.y] = new EmptyCell(new Point(currLoc.x, currLoc.y));
            PacmanStore.setFruitAppear(false);
        }
    }

    /**
     * If Pacman collides with a ghost.
     */
    private Ghost collideWithGhost() {
        return null;
    }

    /**
     * Pacman loses one life, resets all characters.
     */
    private void resetCharacter() {

    }
}
