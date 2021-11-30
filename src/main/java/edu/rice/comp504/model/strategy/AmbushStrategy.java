package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.PacmanStore;
import edu.rice.comp504.model.agent.ACharacter;
import edu.rice.comp504.model.agent.Ghost;
import edu.rice.comp504.model.agent.Pacman;

import java.awt.*;
import java.util.ArrayList;

/**
 * Use for pink. Advanced four units before the pacman direction.
 */
public class AmbushStrategy implements IUpdateStrategy {
    private static IUpdateStrategy singleton;

    /**
     * Make the only random chase strategy.
     *
     * @return A random chase strategy
     */
    public static IUpdateStrategy make() {
        if (singleton == null) {
            singleton = new AmbushStrategy();
        }
        return singleton;
    }

    @Override
    public String getName() {
        return "ambush";
    }

    /**
     * Based on the location of pacman to fix the target.
     *
     * @return a new point
     */
    private Point fixedMarginLoc(Pacman pacman) {
        String[][] grid = PacmanStore.getLetterGrid();
        int dir = pacman.getDir();
        Point cur = pacman.getLoc();
        switch (dir) {
            case 0: // up
                if (cur.x - 4 <= 0) {
                    return new Point(1, cur.y);
                }
                break;
            case 1: // right
                if (cur.y + 4 >= grid[0].length - 1) {
                    return new Point(cur.x, grid[0].length - 2);
                }
                break;
            case 2: // down
                if (cur.x + 4 >= grid.length - 1) {
                    return new Point(grid.length - 2, cur.y);
                }
                break;
            case 3: // left
                if (cur.y - 4 <= 0) {
                    return new Point(cur.x, 1);
                }
                break;
            default:
                break;
        }
        return fixedLoc(cur.x, cur.y, dir, 4, grid);
    }

    /**
     * Use recursion to find the fixed point.
     *
     * @param x      the x
     * @param y      the y
     * @param dir    the direction
     * @param cutoff the cutoff
     * @param grid   the grid
     * @return the point
     */
    public Point fixedLoc(int x, int y, int dir, int cutoff, String[][] grid) {
        if (cutoff == 0) {
            return new Point(x, y);
        }
        switch (dir) {
            case 0:
                if (AStarAlgorithm.ifNeighbor(grid[x - cutoff][y])) {
                    return new Point(x - cutoff, y);
                } else {
                    return fixedLoc(x, y, dir, cutoff - 1, grid);
                }
            case 1:
                if (AStarAlgorithm.ifNeighbor(grid[x][y + cutoff])) {
                    return new Point(x, y + cutoff);
                } else {
                    return fixedLoc(x, y, dir, cutoff - 1, grid);
                }
            case 2:
                if (AStarAlgorithm.ifNeighbor(grid[x + cutoff][y])) {
                    return new Point(x + cutoff, y);
                } else {
                    return fixedLoc(x, y, dir, cutoff - 1, grid);
                }
            case 3:
                if (AStarAlgorithm.ifNeighbor(grid[x][y - cutoff])) {
                    return new Point(x, y - cutoff);
                } else {
                    return fixedLoc(x, y, dir, cutoff - 1, grid);
                }
            default:
                break;
        }
        return new Point(x, y);
    }

    @Override
    public void updateState(ACharacter context, ACharacter context2) {
        //System.out.println("********************************");
        //System.out.println("********************************");
        //System.out.println("********************************");
        Ghost ghost = (Ghost) context;
        Pacman pacman = (Pacman) context2;
        if (ghost.getFresh() || AStarAlgorithm.ifNode(ghost.getLoc().x, ghost.getLoc().y)) {
            //System.out.println("(" + pacman.getLoc().x + ", " + pacman.getLoc().y + ")");
            Point temp = fixedMarginLoc(pacman);
            //System.out.println("(" + temp.x + ", " + temp.y + ")");
            //System.out.println("********************************");
            //System.out.println("********************************");
            //System.out.println("********************************");
            ghost.setPath(AStarAlgorithm.aStarSearch(ghost.getLoc(), fixedMarginLoc(pacman)));
            ghost.setFresh();
        }
        ArrayList<Integer> path = ghost.getPath();
        if (path == null || path.isEmpty()) {
            ;
        } else {
            ghost.setDir(path.get(0));
            ghost.updatePath("path");
        }
        ghost.nextLocation();
    }
}
