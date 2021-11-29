package edu.rice.comp504.model;

//import edu.rice.comp504.model.item.APaintObject;
import edu.rice.comp504.model.agent.Ghost;
import edu.rice.comp504.model.agent.Pacman;
import edu.rice.comp504.model.cmd.InteractCmd;
import edu.rice.comp504.model.cmd.UpdateStateCmd;
import edu.rice.comp504.model.item.*;
import edu.rice.comp504.model.strategy.GhostStrategyFac;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
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
    private static long lastFruitAppearTime;
    private static final long fruitDisappearTime = 5000; //5s
    private static final long fruitAppearTime = 5000; //5s
    private static boolean fruitAppear;
    private static int currentFrame = -1;
    private static int numEatenGhost = 0;

    private static final int[] Ghost_Score_List = new int[]{200,400,800,1600};
    private static final int Dark_Blue_Frames = 100;
    private static final int Blink_Frames = 50;

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
        lastFruitAppearTime = System.currentTimeMillis();
        fruitAppear = true;
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
        // update fruit
        long currentTime = System.currentTimeMillis();
        System.out.println(currentTime+"  "+(lastFruitAppearTime + fruitAppearTime)+" "+(lastFruitAppearTime + fruitAppearTime + fruitDisappearTime));
        if(currentTime >= lastFruitAppearTime + fruitAppearTime && currentTime < lastFruitAppearTime + fruitAppearTime + fruitDisappearTime) {
            setFruitAppear(false);
        } else if(!fruitAppear && currentTime >= lastFruitAppearTime + fruitAppearTime + fruitDisappearTime){
            System.out.println("pass here" + currentTime);
            setFruitAppear(true);
            // TODO: performance issue, 240 dots, acceptable?
            //remove fruit item from eaten items
            Iterator<AItem> iterator = eatenItems.iterator();
            while (iterator.hasNext()) {
                AItem item = iterator.next();
                if (item.getType().equals("fruit")) {
                    iterator.remove();
                }
            }
            // update lastFruitAppearTime
            lastFruitAppearTime = currentTime;
        }
        // update characters
        pcs.firePropertyChange("theClock", false,
                /*pass update cmd*/new InteractCmd(pcs.getPropertyChangeListeners("theClock")));
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

    /**
     * isFruitAppear.
     * @return fruitAppear.
     */
    public static boolean isFruitAppear() {
        return fruitAppear;
    }

    /**
     * setFruitAppear.
     */
    public static void setFruitAppear(boolean fruitAppear) {
        PacmanStore.fruitAppear = fruitAppear;
    }


    /**
     * getScore.
     * @return score.
     */
    public static int getScore() {
        return score;
    }

    /**
     * setScore.
     */
    public static void setScore(int score) {
        PacmanStore.score = score;
    }

    /**
     * getEatenItems.
     * @return eatenItems.
     */
    public static List<AItem> getEatenItems() {
        return eatenItems;
    }

    /**
     * getLastFruitAppearTime.
     * @return lastFruitAppearTime.
     */
    public long getLastFruitAppearTime() {
        return lastFruitAppearTime;
    }

    /**
     * setLastFruitAppearTime.
     * @param lastFruitAppearTime lastFruitAppearTime.
     */
    public void setLastFruitAppearTime(long lastFruitAppearTime) {
        this.lastFruitAppearTime = lastFruitAppearTime;
    }

    /**
     * getCurrentFrame.
     * @return currentFrame.
     */
    public static int getCurrentFrame() {
        return currentFrame;
    }

    /**
     * setCurrentFrame.
     */
    public static void setCurrentFrame(int currentFrame) {
        PacmanStore.currentFrame = currentFrame;
    }

    /**
     * getNumEatenGhost.
     * @return numEatenGhost.
     */
    public static int getNumEatenGhost() {
        return numEatenGhost;
    }

    /**
     * setNumEatenGhost.
     */
    public static void setNumEatenGhost(int numEatenGhost) {
        PacmanStore.numEatenGhost = numEatenGhost;
    }

    /**
     * getDarkBlueFrames.
     * @return Dark_Blue_Frames.
     */
    public static int getDarkBlueFrames() {
        return Dark_Blue_Frames;
    }

    /**
     * getBlinkFrames.
     * @return Blink_Frames.
     */
    public static int getBlinkFrames() {
        return Blink_Frames;
    }

    /**
     * get ghost score lists.
     * @return score lists.
     */
    public static int[] getGhostScoreList() {
        return Ghost_Score_List;
    }


    /**
     * get lives.
     * @return lives.
     */
    public static int getLives() {
        for (int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                APaintObject object = grid[i][j];
                if (object.getType().equals("pacman")) {
                    return ((Pacman)object).getLives();
                }
            }
        }
        return 0;
    }
}
