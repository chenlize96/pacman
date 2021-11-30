package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.PacmanStore;
import edu.rice.comp504.model.agent.ACharacter;
import edu.rice.comp504.model.agent.Ghost;
import edu.rice.comp504.model.item.APaintObject;
import edu.rice.comp504.util.RandUtil;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Flashing pattern.
 */
public class RandomChaseStrategy implements IUpdateStrategy {

    private static IUpdateStrategy singleton;

    /**
     * Constructor.
     */
    private RandomChaseStrategy() {
    }

    /**
     * Make the only random chase strategy.
     *
     * @return A random chase strategy
     */
    public static IUpdateStrategy make() {
        if (singleton == null) {
            singleton = new RandomChaseStrategy();
        }
        return singleton;
    }

    /**
     * Return RandomChase type.
     */
    @Override
    public String getName() {
        return "random";
    }


    /**
     * The helper.
     * @param loc the location
     * @return the array
     */
    public int[] availableDirHelper(Point loc) {
        APaintObject[][] dirs = PacmanStore.getGrid();
        int[] directionTemp = {0,0,0,0};       //index 0: top   ,1:right    ,2: down    ,3:left
        if (loc.x > 0 && !(dirs[loc.x - 1][loc.y].getType().equals("wall"))) {
            directionTemp[0] = 1;
        }
        if (loc.y > 0 && !(dirs[loc.x][loc.y - 1].getType().equals("wall"))) {
            directionTemp[3] = 1;
        }
        if (loc.x < dirs.length - 1 && !(dirs[loc.x + 1][loc.y].getType().equals("wall"))) {
            directionTemp[2] = 1;
        }
        if (loc.y < dirs[0].length - 1  && !(dirs[loc.x][loc.y + 1].getType().equals("wall"))) {
            directionTemp[1] = 1;
        }
        return directionTemp;
    }


    @Override
    /**
     * Update a ghost position randomly.
     */
    public void updateState(ACharacter context, ACharacter context2) {

        Ghost ghost = (Ghost) context;
        int[] dirTemp = {0,0,0,0};
        //System.out.println(context.detectCollisionWithWalls(PacmanStore.getGrid()));
        if (context.detectCollisionWithWalls(PacmanStore.getGrid())) {
            dirTemp = availableDirHelper(ghost.getLoc());
            //System.out.println(context.getLoc());
            //System.out.println(dirTemp[0] + " " + dirTemp[1] + " " + dirTemp[2] + " " + dirTemp[3]);
            int l = 0;
            Random r = new Random();
            for (int i = 0 ; i < 4 ; i++) {
                if (dirTemp[i] == 1) {
                    l++;
                }
            }
            int newDirTemp = (int) (Math.random() * (l + 1));
            //System.out.println(newDirTemp);
            int newDir = 0;
            for (int i = 0 ; i < 4 ; i++) {
                if (newDirTemp == 1 && dirTemp[i] == 1) {
                    newDir = i;
                    break;
                }
                if (dirTemp[i] == 1) {
                    newDirTemp--;
                }
            }
            if (newDir == 0 && dirTemp[0] == 0) {
                if (dirTemp[1] == 1) {
                    newDir = 1;
                }
                if (dirTemp[2] == 1) {
                    newDir = 2;
                }
                if (dirTemp[3] == 1) {
                    newDir = 3;
                }
            }
            ghost.setDir(newDir);

//
//            if(ghost.getDir()==1){
//                ghost.setDir(3);
//            }
//            else{
//                ghost.setDir(1);
//            }

        }
        ghost.nextLocation();

    }
}
