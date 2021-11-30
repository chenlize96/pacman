package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.PacmanStore;
import edu.rice.comp504.model.agent.ACharacter;
import edu.rice.comp504.model.agent.Ghost;
import edu.rice.comp504.model.agent.Pacman;
import edu.rice.comp504.model.item.*;
import edu.rice.comp504.model.strategy.GhostStrategyFac;

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
            // at this point, the pacman might have eaten the bigDot
            // use while, cuzz we can meet many ghosts at the same time
            // we can only collide with ghost whose status is not dead
            while (collideWithGhost(pacman) != null) {
                int currentFrame = PacmanStore.getCurrentFrame();
                if (currentFrame > 0) {
                    // ghost and score related
                    Ghost ghost = collideWithGhost(pacman);
                    ghost.setGhostStatus("dead");
                    // TODO: dead ghost need to return to the home box
                    ghost.setStrategy(GhostStrategyFac.makeStrategyFactory().make("backHome"));

                    PacmanStore.setNumEatenGhost(PacmanStore.getNumEatenGhost() + 1);
                    int[] ghostScoreList = PacmanStore.getGhostScoreList();
                    PacmanStore.setScore(PacmanStore.getScore() + ghostScoreList[Math.min(PacmanStore.getNumEatenGhost(), ghostScoreList.length - 1)]);
                } else if (currentFrame == -1) { // currentFrame == -1
                    Ghost ghost = collideWithGhost(pacman); //todo check ghost's status.
                    resetCharacters(); //Pacman dies for now. todo pacman can eat ghosts.
                    break;
                }
            }
        }

        if (PacmanStore.getCurrentFrame() != -1) {
            PacmanStore.setCurrentFrame(PacmanStore.getCurrentFrame() + 1);
        }
        reborn();
        if (PacmanStore.getCurrentFrame() == PacmanStore.getDarkBlueFrames() + PacmanStore.getBlinkFrames() + 1) {
            // all ghosts set to vulnerable_blink except those dead ones
            becomeNormal();
        } else if (PacmanStore.getCurrentFrame() > PacmanStore.getDarkBlueFrames()) {
            setAllGhostVulnerableBlink();
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
            // all ghosts turn dark-blue for 4 frames, then blink for 4 frames
            // don't need to consider that we eat 2 bigDots in 8 frames, due to the grid size
            setAllGhostVulnerableAndStartTimer();
        } else if (type.equals("strawberry") || type.equals("apple")) {
            if (PacmanStore.isFruitAppear()) {
                PacmanStore.setScore(PacmanStore.getScore() + ((Fruit) grid[currLoc.x][currLoc.y]).score);
                PacmanStore.addEatenItems((Fruit) grid[currLoc.x][currLoc.y]);
                PacmanStore.setFruitAppear(false);
            }
        }
    }

    /**
     * set all ghost vulnerable and start the timer.
     */
    private void setAllGhostVulnerableAndStartTimer() {
        for (PropertyChangeListener pcl : iCharacters) {
            if (((APaintObject) pcl).getType().equals("ghost")) {
                Ghost ghost = (Ghost) pcl;
                ghost.setGhostStatus("vulnerable_dark_blue");
                ghost.setStrategy(GhostStrategyFac.makeStrategyFactory().make("random"));
            }
        }
        PacmanStore.setCurrentFrame(0);
    }

    /**
     * set all ghost VulnerableBlink. Except those dead.
     */
    private void setAllGhostVulnerableBlink() {
        for (PropertyChangeListener pcl : iCharacters) {
            if (((APaintObject) pcl).getType().equals("ghost")) {
                Ghost ghost = (Ghost) pcl;
                if (!ghost.getGhostStatus().equals("dead")) {
                    ghost.setGhostStatus("vulnerable_blink");
                }
            }
        }
    }

    /**
     * game becomes normal.
     */
    private void becomeNormal() {
        for (PropertyChangeListener pcl : iCharacters) {
            if (((APaintObject) pcl).getType().equals("ghost")) {
                Ghost ghost = (Ghost) pcl;
                if (!ghost.getGhostStatus().equals("dead")) {
                    ghost.setGhostStatus("normal");
                    ghost.resetBornStrategy();
                    //ghost.setStrategy(GhostStrategyFac.makeStrategyFactory().make("chase"));
                }
            }
        }
        PacmanStore.setCurrentFrame(-1);
        PacmanStore.setNumEatenGhost(0);
    }

    /**
     * If Pacman collides with a ghost.
     */
    private Ghost collideWithGhost(Pacman pacman) {

        for (PropertyChangeListener pcl : iCharacters) {
            if (((APaintObject) pcl).getType().equals("ghost")) {
                Ghost ghost = (Ghost) pcl;
                // we can only collide with ghost whose status is not dead
                if (ghost.getGhostStatus().equals("dead")) {
                    continue;
                }
                if (ghost.getLoc().equals(pacman.getLoc())) {
                    return ghost;
                }
                //Check the edge case of pacman and ghost switch positions.
                if (ghost.getLoc().distance(pacman.getLoc()) != 1) {
                    continue;
                }
                int dirDiff = Math.abs(ghost.getDir() - pacman.getDir());
                if (dirDiff == 2) {
                    return ghost;
                }
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
            } else {
                ((Ghost)character).resetBornStrategy();
            }
            Point point = character.getBornLoc();
            ((ACharacter) pcl).setLoc(point);
        }
    }

    /**
     * Reborn all ghosts back home.
     */
    private void reborn() {
        for (PropertyChangeListener pcl : iCharacters) {
            if (((APaintObject) pcl).getType().equals("ghost")) {
                Ghost ghost = (Ghost) pcl;
                if (ghost.getGhostStatus().equals("dead") && ghost.getLoc().equals(ghost.getBornLoc())) {
                    ghost.setGhostStatus("normal");
                    ghost.resetBornStrategy();
                    //ghost.setStrategy(GhostStrategyFac.makeStrategyFactory().make("chase"));
                }
            }
        }
    }
}
