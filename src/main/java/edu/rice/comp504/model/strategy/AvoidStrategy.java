package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.PacmanStore;
import edu.rice.comp504.model.agent.ACharacter;
import edu.rice.comp504.model.agent.Ghost;
import edu.rice.comp504.model.agent.Pacman;
import edu.rice.comp504.model.item.APaintObject;

import java.awt.*;
import java.util.Random;

public class AvoidStrategy implements IUpdateStrategy {

    private static IUpdateStrategy singleton;

    /**
     * Make the only random chase strategy.
     *
     * @return A random chase strategy
     */
    public static IUpdateStrategy make() {
        if (singleton == null) {
            singleton = new AvoidStrategy();
        }
        return singleton;
    }

    /**
     * Return RandomChase type.
     */
    @Override
    public String getName() {
        return "avoid";
    }

    /**
     * The helper.
     *
     * @param loc the location
     * @return the array
     */
    public int[] availableDirHelper(Point loc) {
        APaintObject[][] dirs = PacmanStore.getGrid();
        int[] directionTemp = {0, 0, 0, 0};       //index 0: top   ,1:right    ,2: down    ,3:left
        if (loc.x > 0 && !(dirs[loc.x - 1][loc.y].getType().equals("wall"))) {
            directionTemp[0] = 1;
        }
        if (loc.y > 0 && !(dirs[loc.x][loc.y - 1].getType().equals("wall"))) {
            directionTemp[3] = 1;
        }
        if (loc.x < dirs.length - 1 && !(dirs[loc.x + 1][loc.y].getType().equals("wall"))) {
            directionTemp[2] = 1;
        }
        if (loc.y < dirs[0].length - 1 && !(dirs[loc.x][loc.y + 1].getType().equals("wall"))) {
            directionTemp[1] = 1;
        }
        return directionTemp;
    }

    /**
     * Random available direction.
     * @param dirTemp the direction
     * @param context the context
     * @param ghost the ghost
     */
    public void randomAvailDir(int[] dirTemp, ACharacter context, Ghost ghost) {
        int l = 0;
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            if (dirTemp[i] == 1) {
                l++;
            }
        }
        int newDirTemp = (int) (Math.random() * (l + 1));
        System.out.println(newDirTemp);
        int newDir = 0;
        for (int i = 0; i < 4; i++) {
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
    }


    @Override
    /**
     * Update a ghost position randomly.
     */
    public void updateState(ACharacter context, ACharacter context2) {
        Ghost ghost = (Ghost) context;
        Pacman pacman = (Pacman) context2;
        Point target = pacman.getLoc();   //the pacman loction
        int nextDir = -1;                  //next direction
        int maxDistance = 0;              //the max distance for next jump
        int currDistance = 0;             //current checking direction's distance from the pacment to ghost
        Point nextGhostPos = ghost.getLoc(); //current checking direction's next jump pos for ghost
        int[] dirTemp = {0, 0, 0, 0};
        //System.out.println(context.detectCollisionWithWalls(PacmanStore.getGrid()));
//        if (context.detectCollisionWithWalls(PacmanStore.getGrid())) {
        dirTemp = availableDirHelper(ghost.getLoc());
        for (int i = 0; i < 4; i++) {
            nextGhostPos = ghost.getLoc();
            currDistance = 0;
            if (dirTemp[i] == 1) {
                if (i == 0) {
                    int tmpX = nextGhostPos.x - 1;
                    currDistance = Math.abs(tmpX - target.x) + Math.abs(nextGhostPos.y - target.y);
                    if (currDistance > maxDistance) {
                        maxDistance = currDistance;
                        nextDir = i;
                    }
                }
                if (i == 1) {
                    int tmpY = nextGhostPos.y + 1;
                    currDistance = Math.abs(nextGhostPos.x - target.x) + Math.abs(tmpY - target.y);
                    if (currDistance > maxDistance) {
                        maxDistance = currDistance;
                        nextDir = i;
                    }
                }
                if (i == 2) {
                    int tmpX = nextGhostPos.x + 1;
                    currDistance = Math.abs(tmpX - target.x) + Math.abs(nextGhostPos.y - target.y);
                    if (currDistance > maxDistance) {
                        maxDistance = currDistance;
                        nextDir = i;
                    }
                }
                if (i == 3) {
                    int tmpY = nextGhostPos.y - 1;
                    currDistance = Math.abs(nextGhostPos.x - target.x) + Math.abs(tmpY - target.y);
                    if (currDistance > maxDistance) {
                        maxDistance = currDistance;
                        nextDir = i;
                    }
                }

            }
        }
//            if (ghost.getName().equals("red")) {
//                System.out.println(context.getLoc());
//                System.out.println(dirTemp[0] + " " + dirTemp[1] + " " + dirTemp[2] + " " + dirTemp[3]);
//                System.out.println(nextDir);
//            }
        if (maxDistance <= 7) {
            if (nextDir == -1) {
                randomAvailDir(dirTemp, context, ghost);
            } else {
                ghost.setDir(nextDir);
            }
        } else {
            if (context.detectCollisionWithWalls(PacmanStore.getGrid())) {
                randomAvailDir(dirTemp, context, ghost);
            }
        }
        ghost.nextLocation();
    }
}
