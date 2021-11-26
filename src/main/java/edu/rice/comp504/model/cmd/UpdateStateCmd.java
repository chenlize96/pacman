package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.PacmanStore;
import edu.rice.comp504.model.agent.ACharacter;
import edu.rice.comp504.model.agent.Ghost;
import edu.rice.comp504.model.agent.Pacman;
import edu.rice.comp504.model.item.*;

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
    public void execute(ACharacter context) {
        //System.out.println("execute");
        if (context instanceof Pacman) {
                //System.out.println("here");
            if (!context.detectCollisionWithWalls(PacmanStore.getGrid())) {
                context.nextLocation();
                //eat a dot if there's a dot
                APaintObject[][] grid = PacmanStore.getGrid();
                Point currLoc = context.getLoc();
                String type = grid[currLoc.x][currLoc.y].getType();
                if(type.equals("dot")) {
                    PacmanStore.setScore(PacmanStore.getScore() + ((Dot)grid[currLoc.x][currLoc.y]).score);
                    PacmanStore.addEatenItems((Dot)grid[currLoc.x][currLoc.y]);
                } else if(type.equals("bigDot")) {
                    PacmanStore.setScore(PacmanStore.getScore() + ((BigDot)grid[currLoc.x][currLoc.y]).score);
                    PacmanStore.addEatenItems((BigDot)grid[currLoc.x][currLoc.y]);
                } else if(type.equals("fruit")) {
                    PacmanStore.setScore(PacmanStore.getScore() + ((Fruit)grid[currLoc.x][currLoc.y]).score);
                    PacmanStore.addEatenItems((Fruit)grid[currLoc.x][currLoc.y]);
                    PacmanStore.setFruitAppear(false);
                }
            }
        } else {
            ((Ghost)context).getStrategy().updateState(context,context);
        }

    }
}
