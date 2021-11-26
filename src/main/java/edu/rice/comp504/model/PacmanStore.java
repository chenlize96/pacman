package edu.rice.comp504.model;

//import edu.rice.comp504.model.item.APaintObject;
import edu.rice.comp504.model.agent.Ghost;
import edu.rice.comp504.model.agent.Pacman;
import edu.rice.comp504.model.cmd.UpdateStateCmd;
import edu.rice.comp504.model.item.*;
import edu.rice.comp504.model.strategy.GhostStrategyFac;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;


/**
 * A store containing current pacman World.
 */
public class PacmanStore {
    private static PropertyChangeSupport pcs;
    private static Point dims;
    private static APaintObject[][] grid;
    private static int score;
    private static List<AItem> eatenItems;

    /**
     * Constructor.
     */
    public PacmanStore() {
        pcs = new PropertyChangeSupport(this);
        //grid = new APaintObject[row][col];
    }

    /**
     * Initialize the game.
     * @param info the letter map
     */
    public void initialize(String[][] info) {
        score = 0;
        eatenItems = new ArrayList<>();
        grid = new APaintObject[info.length][info[0].length];
        APaintObject obj;
        for (int row = 0; row < info.length; row++) {
            for (int col = 0; col < info[row].length; col++) {
                Point loc = new Point(row, col);
                switch (info[row][col]) {
                    case "W":
                    case "B":
                    case "b":
                    case "R":
                        obj = new Wall(loc);
                        break;
                    case "D":
                        obj = new Dot(loc);
                        break;
                    case "L":
                        obj = new BigDot(loc);
                        break;
                    case "E":
                        obj = new EmptyCell(loc);
                        break;
                    case "S":
                        obj = new Fruit(loc,1000);
                        break;
                    case "P":
                        obj = new Pacman("pacman", loc, loc, 1); // dir = right
                        addCharacterToStore((PropertyChangeListener) obj);
                        break;
                    case "T":
                        obj = new TransportCell(loc);
                        break;
                    case "1":
                        obj = new Ghost("ghost", loc, loc, 1); // dir = right
                        ((Ghost) obj).setName("red");
                        addCharacterToStore((PropertyChangeListener) obj);
                        break;
                    case "2":
                        obj = new Ghost("ghost", loc, loc, 1); // dir = right
                        ((Ghost) obj).setName("pink");
                        addCharacterToStore((PropertyChangeListener) obj);
                        break;
                    case "3":
                        obj = new Ghost("ghost", loc, loc, 1); // dir = right
                        ((Ghost) obj).setName("orange");
                        addCharacterToStore((PropertyChangeListener) obj);
                        break;
                    case "4":
                        obj = new Ghost("ghost", loc, loc, 1); // dir = right
                        ((Ghost) obj).setName("cyan");
                        addCharacterToStore((PropertyChangeListener) obj);
                        break;
                    default:
                        obj = null;
                        break;
                }
                grid[row][col] = obj;
            }
        }
    }

    /**
     * Return the grid.
     * @return grid
     */
    public static APaintObject[][] getGrid() {
        return grid;
    }

    /**
     * Create the ghost strategy.
     * @return the ghost strategy
     */
    public static GhostStrategyFac getOnlyStratFac() {
        /*if (ONLY == null) {
            ONLY = new GhostStrategyFac();
        }
        return ONLY;*/
        return null;
    }


    /**
     * add an eaten item to the list.
     * @param item eaten item.
     */
    public static void addEatenItems(AItem item) {
        eatenItems.add(item);
    }

    /**
     * Get the canvas dimensions.
     * @return The canvas dimensions
     */
    public static Point getCanvasDims() {
        return dims;
    }

    /**
     * Set the canvas dimensions.
     * @param dims The canvas width (x) and height (y).
     */
    public void setCanvasDims(Point dims) {
        this.dims = dims;
    }

    /**
     * Call the update method on all the character observers to update their position in the pacman world.
     */
    public static PropertyChangeListener[] updatePacmanWorld() {
        pcs.firePropertyChange("theClock", false,
                /*pass update cmd*/new UpdateStateCmd(pcs.getPropertyChangeListeners("theClock")));
        return pcs.getPropertyChangeListeners();
    }

    /**
     * Get all the listeners.
     * @return the listeners
     */
    public PropertyChangeListener[] getListeners() {
        return pcs.getPropertyChangeListeners();
    }

    /**
     * Switch the strategy of ghosts.
     */
    public PropertyChangeListener[] switchStrategy(String strat) {
        //System.out.println("1");
        for (PropertyChangeListener l : pcs.getPropertyChangeListeners("theClock")) {
            //switchStrategyCmd.execute((APaintObject) l, strat);
        }
        return null;
    }

    /**
     * Set the current direction of pacman.
     * @param curDir the current direction
     */
    public static void setPacmanDir(int curDir) {
        for (PropertyChangeListener l : pcs.getPropertyChangeListeners("theClock")) {
            if (l instanceof Pacman && curDir >= 0 && curDir <= 3) {
                ((Pacman) l).setDir(curDir);
            }
        }
    }

    /**
     * Add a character that will listen for a property change (i.e. time elapsed)
     * @param pcl  The ball
     */
    public static void addCharacterToStore(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener("theClock", pcl);
    }

    /**
     * Remove all characters from listening for property change events for a particular property.
     */
    public void removeCharactersFromStore() {
        for (PropertyChangeListener l : pcs.getPropertyChangeListeners()) {
            pcs.removePropertyChangeListener("theClock", l);
        }
    }

    /**
     * Remove the target property change listeners.
     * @param wall  The inner wall
     */
    public static void removeTheGhost(PropertyChangeListener wall) {
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        PacmanStore.score = score;
    }

    public static List<AItem> getEatenItems() {
        return eatenItems;
    }
}
