package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.PacmanStore;
import edu.rice.comp504.model.agent.ACharacter;
import edu.rice.comp504.model.agent.Ghost;
import edu.rice.comp504.model.agent.Pacman;

import java.beans.PropertyChangeListener;

public class UpdateStateCmd implements IPaintObjCmd {


    private final PropertyChangeListener[] iCharacters;

    /**
     * The constructor.
     * @param iCharacters pacman and ghosts
     *
     */
    public UpdateStateCmd(PropertyChangeListener[] iCharacters) {
        this.iCharacters = iCharacters;
    }

    @Override
    public void execute(ACharacter context) {
        //System.out.println("execute");
        if (context instanceof Pacman) {
                //System.out.println("here");
            if (!context.detectCollisionWithWalls(PacmanStore.getGrid())) {
                context.nextLocation();
            }
        } else {
            ((Ghost)context).getStrategy().updateState(context,context);
        }

    }
}
