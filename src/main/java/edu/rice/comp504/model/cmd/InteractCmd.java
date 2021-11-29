package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.PacmanStore;
import edu.rice.comp504.model.agent.ACharacter;
import edu.rice.comp504.model.agent.Ghost;
import edu.rice.comp504.model.agent.Pacman;
import edu.rice.comp504.model.item.APaintObject;

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
            if (collideWithDots()) {
                pacman.setScore(pacman.getScore() + 20);
            }
            if (collideWithGhost()!=null) {
                Ghost ghost = collideWithGhost();
                resetCharacter(); //Pacman dies or eat the ghost.
            }
        }

    }

    /**
     * If Pacman collides with a dot.
     */
    private boolean collideWithDots() {
        APaintObject[][] map = PacmanStore.getGrid();
        return false;
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
