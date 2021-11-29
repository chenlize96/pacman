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
            if (collideWithGhost(pacman) != null) {
                Ghost ghost = collideWithGhost(pacman); //todo check ghost's status.
                resetCharacters(); //Pacman dies for now. todo pacman can eat ghosts.
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
        if (type.equals("dot")) {
            PacmanStore.setScore(PacmanStore.getScore() + ((Dot) grid[currLoc.x][currLoc.y]).score);
            PacmanStore.addEatenItems((Dot) grid[currLoc.x][currLoc.y]);
            grid[currLoc.x][currLoc.y] = new EmptyCell(new Point(currLoc.x, currLoc.y));
        } else if (type.equals("bigDot")) {
            PacmanStore.setScore(PacmanStore.getScore() + ((BigDot) grid[currLoc.x][currLoc.y]).score);
            PacmanStore.addEatenItems((BigDot) grid[currLoc.x][currLoc.y]);
            grid[currLoc.x][currLoc.y] = new EmptyCell(new Point(currLoc.x, currLoc.y));
        } else if (type.equals("fruit")) {
            PacmanStore.setScore(PacmanStore.getScore() + ((Fruit) grid[currLoc.x][currLoc.y]).score);
            PacmanStore.addEatenItems((Fruit) grid[currLoc.x][currLoc.y]);
            grid[currLoc.x][currLoc.y] = new EmptyCell(new Point(currLoc.x, currLoc.y));
            PacmanStore.setFruitAppear(false);
        }
    }

    /**
     * If Pacman collides with a ghost.
     */
    private Ghost collideWithGhost(Pacman pacman) {

        for (PropertyChangeListener pcl : iCharacters) {
            if (((APaintObject) pcl).getType().equals("ghost")) {
                Ghost ghost = (Ghost) pcl;
                if (ghost.getLoc().equals(pacman.getLoc())) {
                    return ghost;
                }
                //Check the edge case of pacman and ghost switch positions.
                if (ghost.getLoc().distance(pacman.getLoc()) != 1) continue;
                int dirDiff = Math.abs(ghost.getDir() - pacman.getDir());
                if (dirDiff == 2) return ghost;
            }
        }

        return null;
    }

    /**
     * Pacman loses one life, resets all characters.
     */
    private void resetCharacters() {
        for (PropertyChangeListener pcl : iCharacters) {
            ACharacter character = ((ACharacter) pcl);
            if (character.getType().equals("pacman")) {
                Pacman pacman = (Pacman) character;
                int lives = pacman.getLives();
                pacman.setLives(lives - 1); //todo check if pacman has lives, 0 live alert and refresh game.
            }
            Point point = character.getBornLoc();
            ((ACharacter) pcl).setLoc(point);
        }
    }
}
