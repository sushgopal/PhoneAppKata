package com.phoneappkata.leastresistancepath;

import java.util.Set;

import static com.phoneappkata.leastresistancepath.LeastResistancePath.NO_NEIGHBOR;

public class ResistancePathFinder {

    private Grid grid;

    private VisitedNodes visitedNodes = new VisitedNodes();

    public LeastResistancePath findAt(int row, int column) {
        Set<Integer> neighborRows = grid.getNeighborRowsFor(row, column);
        LeastResistancePath leastResistanceNeighborPath = NO_NEIGHBOR;

        if(exists(neighborRows)) {
            leastResistanceNeighborPath = findLeastResistanceNeighbor(column, neighborRows);
        }

        LeastResistancePath path = new LeastResistancePath(grid, row, column, leastResistanceNeighborPath);
        visitedNodes.add(row, column, path);
        return path;
    }

    private LeastResistancePath findLeastResistanceNeighbor(int column, Set<Integer> neighborRows) {
        LeastResistancePath leastResistanceNeighborPath = null;
        int neighborColumn = next(column);

        for (Integer neighborRow : neighborRows) {
            LeastResistancePath currentNeighborPath;

            if (visitedNodes.visited(neighborRow, neighborColumn)) {
                currentNeighborPath = visitedNodes.get(neighborRow,  neighborColumn);
            } else {
                currentNeighborPath = findAt(neighborRow, neighborColumn);
            }

            if(isCurrentResistanceLessThanLeastResistance(currentNeighborPath, leastResistanceNeighborPath)) {
                leastResistanceNeighborPath = currentNeighborPath;
            }
        }

        return leastResistanceNeighborPath;
    }

    private boolean isCurrentResistanceLessThanLeastResistance(LeastResistancePath currentPath, LeastResistancePath leastResistancePath) {
        return leastResistancePath == null || currentIsLessThanLeast(currentPath, leastResistancePath);
    }

    private boolean currentIsLessThanLeast(LeastResistancePath currentPath, LeastResistancePath leastResistancePath) {
        return currentPath.getResistance() < leastResistancePath.getResistance();
    }

    private int next(int column) {
        return column + 1;
    }

    private boolean exists(Set<Integer> collection) {
        return !collection.isEmpty();
    }

    public ResistancePathFinder setGrid(Grid grid) {
        this.grid = grid;
        return this;
    }

}
