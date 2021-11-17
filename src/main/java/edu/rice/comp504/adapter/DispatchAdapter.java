package edu.rice.comp504.adapter;

import edu.rice.comp504.model.PacmanStore;


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
     * @param fileName the file name, named by the level of the game.
     */
    public String[][] initializeLevel(String fileName) {
        String input = "W W W W W W W W W W W W W W W W W W W W W W W W W W W W\n" +
                "W L D D D D D D D D D D D W W D D D D D D D D D D D L W\n" +
                "W D W W W W D W W W W W D W W D W W W W W D W W W W D W\n" +
                "W D W W W W D W W W W W D W W D W W W W W D W W W W D W\n" +
                "W D W W W W D W W W W W D W W D W W W W W D W W W W D W\n" +
                "W D D D D D D D D D D D D D D D D D D D D D D D D D D W\n" +
                "W D W W W W D W W D W W W W W W W W D W W D W W W W D W\n" +
                "W D W W W W D W W D W W W W W W W W D W W D W W W W D W\n" +
                "W D D D D D D W W D D D D W W D D D D W W D D D D D D W\n" +
                "W W W W W W D W W W W W D W W D W W W W W D W W W W W W\n" +
                "W W W W W W D W W W W W E W W E W W W W W D W W W W W W\n" +
                "W W W W W W D W W E E E E E E E E E E W W D W W W W W W\n" +
                "W W W W W W D W W E W E W W W W E W E W W D W W W W W W\n" +
                "W W W W W W D W W E W E 1 E 3 E E W E W W D W W W W W W\n" +
                "E E E S E E D E E E W E E 2 E 4 E W E E E D E E E E E E\n" +
                "W W W W W W D W W E W W W W W W W W E W W D W W W W W W\n" +
                "W W W W W W D W W E E E E E E E E E E W W D W W W W W W\n" +
                "W W W W W W D W W E W W W W W W W W E W W D W W W W W W\n" +
                "W W W W W W D W W E W W W W W W W W E W W D W W W W W W\n" +
                "W W W W W W D W W E W W W W W W W W E W W D W W W W W W\n" +
                "W D D D D D D D D D D D D W W D D D D D D D D D D D D W\n" +
                "W D W W W W D W W W W W D W W D W W W W W D W W W W D W\n" +
                "W D W W W W D W W W W W D W W D W W W W W D W W W W D W\n" +
                "W D D D W W D D D D D D D D D D D D D D D D W W D D D W\n" +
                "W W W D W W D W W D W W W W W W W W D W W D W W D W W W\n" +
                "W W W D W W D W W D W W W W W W W W D W W D W W D W W W\n" +
                "W D D D D D D W W D D D D W W D D D D W W D D D D D D W\n" +
                "W D W W W W W W W W W W D W W D W W W W W W W W W W D W\n" +
                "W L D D D D D D D D D D D D D P D D D D D D D D D D L W\n" +
                "W W W W W W W W W W W W W W W W W W W W W W W W W W W W";
        row = 0;
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
        }
        System.out.println(sum);
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
     * @return pacman and ghosts
     */
    public PropertyChangeListener[] updatePacmanWorld() {
        return PacmanStore.updatePacmanWorld();
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