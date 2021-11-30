package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.PacmanStore;
import junit.framework.TestCase;

import java.awt.*;
import java.util.ArrayList;

public class AStarAlgorithmTest extends TestCase {

    public void testAStarSearch() {
        Point start = new Point(12, 13);
        Point end = new Point(PacmanStore.getLetterGrid().length - 2, 15);
        ArrayList<Integer> path = AStarAlgorithm.aStarSearch(start, end);
    }

    public void testIfNode() {
        boolean flag = AStarAlgorithm.ifNode(0, 0);
        flag = AStarAlgorithm.ifNode(12, 13);
    }

    public void testIfNeighbor() {
        boolean flag = AStarAlgorithm.ifNeighbor("W");
        flag = AStarAlgorithm.ifNeighbor("B");
        flag = AStarAlgorithm.ifNeighbor("b");
        flag = AStarAlgorithm.ifNeighbor("R");
        flag = AStarAlgorithm.ifNeighbor("T");

    }

    public void testManhattanHeuristic() {
        Point start = new Point(12, 13);
        Point end = new Point(PacmanStore.getLetterGrid().length - 2, 15);
        int x = AStarAlgorithm.manhattanHeuristic(start, end);
    }
}