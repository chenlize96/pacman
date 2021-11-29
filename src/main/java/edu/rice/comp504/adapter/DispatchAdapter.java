package edu.rice.comp504.adapter;

import edu.rice.comp504.model.LevelStore;
import edu.rice.comp504.model.PacmanStore;
import edu.rice.comp504.model.ResponseBody;
import edu.rice.comp504.model.item.AItem;


import java.awt.*;
import java.beans.PropertyChangeListener;


/**
 * This adapter interfaces with the view (paint objects) and the controller.
 */
public class DispatchAdapter {

    private PacmanStore pacmanStore;
    private String[][] grid;
    private int row;
    private int col;

    /**
     * Constructor call.
     */
    public DispatchAdapter() {
        pacmanStore = new PacmanStore();
    }

    /**
     * initialize the game.
     * @return fileName the file name, named by the level of the game.
     */
    /*public APaintObject[][] initialize() {
        pacmanStore.initialize("easy");
        for (int i=0; i<pacmanStore.getGrid().length; i++) {
            for(int j=0; j<pacmanStore.getGrid()[0].length; j++) {
                System.out.println(pacmanStore.getGrid()[i][j].getName());
            }
        }
        return pacmanStore.getGrid();
    }*/

    /**
     * initialize the game. (For Dinghua, API Design Section)
     * @param level the file name, named by the level of the game.
     */
    public String[][] initializeLevel(String level) {
        String[][] grid = LevelStore.getTheLevel(level);
        /*row = 0;
        col = 0;
        String[] lineSplit = input.split("\n");
        while (row < lineSplit.length) {
            String[] line = lineSplit[row].split(" ");
            col += line.length;
            row++;
        }
        col = col / row;
        grid = new String[row][col];
        //
        row = 0;
        int sum = 0; // indicate the number of small dots
        while (row < lineSplit.length) {
            String[] line = lineSplit[row].split(" ");
            for (col = 0; col < line.length; col++) {
                if (line[col].equals("D")) {
                    sum++;
                }
                grid[row][col] = line[col];
            }
            row++;
        }*/
        pacmanStore.initialize(grid);
        return grid;
    }

    /**
     * Set the canvas.
     * @param p canvas
     */
    public int setCanvasDims(Point p) {
        return 0;
    }

    /**
     * Update the pacman world.
     * @return pacman, ghost, score, eaten items.
     */
    public ResponseBody updatePacmanWorld() {
        PropertyChangeListener[] dynamics = PacmanStore.updatePacmanWorld();
        AItem[] eaten = new AItem[PacmanStore.getEatenItems().size()];
        PacmanStore.getEatenItems().toArray(eaten);
        return new ResponseBody(dynamics,eaten,PacmanStore.getScore(),PacmanStore.isFruitAppear());
    }

    /**
     * Switch the strategy for ghosts.
     * @return characters in pacman world
     */
    public PropertyChangeListener[] switchStrategy(String strat) {
        return null;
    }

    /**
     * Set the interaction between the ghosts and pacman.
     * @return characters in pacman world
     */
    public PropertyChangeListener[] setInteraction(String interaction) {
        return null;
    }

    /**
     * Remove the strategy.
     * @param strategy the strategy
     * @return characters in pacman world
     */
    public PropertyChangeListener[] removeStrategy(String strategy) {
        return null;
    }

    /**
     * Remove all ghosts and pacman from listening for property change events for a particular property.
     * @return pacman in Pacman
     */
    public PropertyChangeListener[] removeAll() {
        pacmanStore.removeCharactersFromStore();
        PacmanStore.setScore(0);
        PacmanStore.getEatenItems().clear();
        return pacmanStore.getListeners();
    }

    /**
     * Get the canvas dim.
     * @return the point
     */
    public Point getCanvasDims() {
        return null;
    }

    /**
     * Get the pacman store.
     * @return pacman store
     */
    public PacmanStore getPacmanStore() {
        return pacmanStore;
    }

    /**
     * Set pacman direction.
     * @param dir the direction of pacman
     */
    public void setPacmanDir(int dir) {
        pacmanStore.setPacmanDir(dir);
    }

    /**
     * Set the pacman store.
     * @param pacmanStore the pacman store
     */
    public void setPacmanStore(PacmanStore pacmanStore) {
        this.pacmanStore = pacmanStore;
    }
}