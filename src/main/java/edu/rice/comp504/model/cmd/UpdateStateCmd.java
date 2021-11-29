package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.PacmanStore;
import edu.rice.comp504.model.agent.ACharacter;
import edu.rice.comp504.model.agent.Ghost;
import edu.rice.comp504.model.agent.Pacman;
import edu.rice.comp504.model.item.APaintObject;
import edu.rice.comp504.model.item.BigDot;
import edu.rice.comp504.model.item.Dot;
import edu.rice.comp504.model.item.Fruit;

import java.awt.*;
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
    public void execute(ACharacter context) {   //add
        //System.out.println("execute");
        if (context instanceof Pacman) {
                //System.out.println("here");
            if (!context.detectCollisionWithWalls(PacmanStore.getGrid())) {
                context.nextLocation();
            }
        } else {
            for (PropertyChangeListener iCharacter : iCharacters) {
                if (((APaintObject)iCharacter).getType().equals("pacman")) {
                    ((Ghost)context).getStrategy().updateState(context,((Pacman)iCharacter));
                    break;
                }
            }
        }

    }
}
