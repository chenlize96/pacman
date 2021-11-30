package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.PacmanStore;

import java.awt.*;
import java.util.*;

/**
 * A star Algorithm with manhattan heuristic.
 * @author Lize Chen
 */
public class AStarAlgorithm {
    /*public static void main(String[] args) {
        // Only used for testing. Do not delete.
        ArrayList<Integer> result = aStarSearch(new Point(1,1), new Point(5, 5));
        Iterator value = result.iterator();
        while (value.hasNext()) {
            System.out.println(value.next());
        }
    }*/

    /**
     * A data structure which stores point, cost, path.
     */
    public static class Data implements Comparable<Data>{
        private Point point;
        private int cost;
        private ArrayList<Integer> path;

        /**
         * Constructor.
         * @param point the point
         * @param cost the cost
         * @param path the path
         */
        public Data(Point point, int cost, ArrayList<Integer> path) {
            this.point = point;
            this.cost = cost;
            this.path = path;
        }

        @Override
        public int compareTo(Data temp) {
            if (this.cost < temp.cost) {
                return -1;
            } else if (temp.cost < this.cost) {
                return 1;
            }
            return 0;
        }
    }

    /**
     * A star searching algorithm.
     * @param start the start point
     * @param end the goal point
     * @return the path
     */
    public static ArrayList<Integer> aStarSearch(Point start, Point end) {
        String[][] grid = PacmanStore.getLetterGrid();
        // 0 is top, 1 is right, 2 is down, 3 is left, -1 is not moving
        /*String[][] grid = {
                { "W", "W", "W", "W", "W", "W", "W" },
                { "W", "1", "W", "D", "D", "2", "W" },
                { "W", "E", "W", "E", "W", "3", "W" },
                { "W", "D", "D", "F", "W", "E", "W" },
                { "W", "W", "D", "W", "W", "D", "W" },
                { "W", "W", "D", "D", "W", "P", "W" },
                { "W", "W", "W", "W", "W", "W", "W" }
        };*/
        PriorityQueue<Data> frontier = new PriorityQueue<>();
        frontier.add(new Data(start, 0, new ArrayList<>()));
        HashSet<Point> visited = new HashSet<>();
        Data current;
        while (!frontier.isEmpty()) {
            current = frontier.poll();
            //System.out.println("("+current.point.x +", "+ current.point.y+") " + current.cost + " path = "+
            //        current.path.toString());
            if (!visited.contains(current.point) ){
                visited.add(current.point);
                if (current.point.equals(end)) {
                    return current.path;
                }
                Set<Object[]> neighbors = getNeighbors(grid, current.point.x, current.point.y);
                for (Object[] neighbor : neighbors) {
                    Point temp = (Point)neighbor[0];
                    int dir = (int)neighbor[1];
                    if (!visited.contains(temp) ){
                        int totalCost = current.cost + 1 + manhattanHeuristic(temp, end);
                        frontier = update(frontier, temp, totalCost, dir, current.path);
                    }
                }
            }
        }
        return null;
    }

    /**
     * Check if the location is a valid node.
     * @param x the x
     * @param y the y
     * @return the boolean
     */
    public static boolean ifNode(int x, int y) {
        String[][] grid = PacmanStore.getLetterGrid();
        if (y + 1 > grid[0].length - 1 || y - 1 < 0 ||
        x - 1 < 0 || x + 1 > grid.length - 1) {
            return true;
        }
        boolean[] dirs = new boolean[] {ifNeighbor(grid[x - 1][y]), ifNeighbor(grid[x][y + 1]),
                ifNeighbor(grid[x + 1][y]), ifNeighbor(grid[x][y - 1])};
        boolean one = dirs[0] && dirs[1];
        boolean two = dirs[1] && dirs[2];
        boolean three = dirs[2] && dirs[3];
        boolean four = dirs[3] && dirs[0];
        return one || two || three || four;
    }

    /**
     * Update the frontier.
     * @param frontier the priority queue
     * @param temp the previous point
     * @param totalCost the total cost
     * @param dir the direction
     * @param path the previous path
     * @return the frontier
     */
    private static PriorityQueue<Data> update(PriorityQueue<Data> frontier, Point temp,
                                              int totalCost, int dir, ArrayList<Integer> path) {
        Iterator pq = frontier.iterator();
        while (pq.hasNext()) {
            Data data = (Data)pq.next();
            if (data.point.equals(temp) && data.cost > totalCost) {
                // compare & update
                pq.remove();
                break;
            } else if (data.point.equals(temp) && data.cost <= totalCost) {
                return frontier;
            }
        }
        ArrayList<Integer> newPath = (ArrayList<Integer>) path.clone();
        newPath.add(dir);
        frontier.add(new Data(temp, totalCost, newPath));
        return frontier;
    }

    /**
     * Get neighbors of the current point.
     * @param grid the letter map
     * @param x the x
     * @param y the y
     * @return the neighbors
     */
    private static Set<Object[]> getNeighbors (String[][] grid, int x, int y) {
        // 0 is top, 1 is right, 2 is down, 3 is left, -1 is not moving
        Set<Object[]> neighbors = new HashSet<>();
        if (x - 1 < 0) {
            neighbors.add(new Object[]{new Point(0, y), 0});
        } else if (ifNeighbor(grid[x - 1][y])) {
            neighbors.add(new Object[]{new Point(x - 1, y), 0});
        }
        if (x + 1 > grid.length - 1) {
            neighbors.add(new Object[]{new Point(grid.length - 1, y), 2});
        } else if (ifNeighbor(grid[x + 1][y])) {
            neighbors.add(new Object[]{new Point(x + 1, y), 2});
        }
        if (y - 1 < 0) {
            neighbors.add(new Object[]{new Point(x, 0), 3});
        } else if (ifNeighbor(grid[x][y - 1])) {
            neighbors.add(new Object[]{new Point(x, y - 1), 3});
        }
        if (y + 1 > grid[0].length - 1) {
            neighbors.add(new Object[]{new Point(x, grid[0].length - 1), 1});
        } else if (ifNeighbor(grid[x][y + 1])) {
            neighbors.add(new Object[]{new Point(x, y + 1), 1});
        }
        return neighbors;
    }

    /**
     * Check if the neighbor is valid.
     * @param cur the current point
     * @return boolean
     */
    public static boolean ifNeighbor(String cur) {
        switch (cur) {
            case "W":
            case "B":
            case "b":
            case "R":
            case "T":
                return false;
        }
        return true;
    }

    /**
     * Set manhattan as the heuristic function.
     * @param cur the current point
     * @param goal the target point
     * @return the heuristic value
     */
    public static int manhattanHeuristic(Point cur, Point goal) {
        return Math.abs(cur.x - goal.x) + Math.abs(cur.y - goal.y);
    }
}
