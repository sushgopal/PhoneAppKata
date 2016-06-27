package com.phoneappkata.leastresistancepath;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Set;

public class LeastResistancePathFinder {

    private Grid grid;

//    public ResistancePathFinder(Grid grid) {
//        this.grid = grid;
//    }

    public ResistancePath find(Grid gridArray) {
        this.grid = gridArray;

        return findAt(grid.getRootRow(), grid.getRootColumn(), new ResistancePath());
    }

    ResistancePath findAt(int row, int column, ResistancePath currentPath) {
        Set<Integer> neighborRows = grid.getNeighborRowsFor(row, column);
        ResistancePath leastResistancePath;
        if(exists(neighborRows)) {
            leastResistancePath = findLeastResistanceNeighbor(column, neighborRows, currentPath);
        }
        else {
            leastResistancePath = currentPath;
        }
        return leastResistancePath;
    }

    private ResistancePath findLeastResistanceNeighbor(int column, Set<Integer> neighborRows, ResistancePath currentPath) {
        ResistancePath leastResistanceNeighborPath = null;
        int neighborColumn = next(column);

        for (Integer neighborRow : neighborRows) {
            ResistancePath currentNeighborPath;

            if(currentPath.isNeighborBlockingFlow(grid, neighborRow, neighborColumn)) {
                currentNeighborPath = currentPath.buildBlockedPath();//new ResistancePath(currentPath.getResistance(), currentPath.getPath(), false);
            } else {
                currentNeighborPath = findAt(neighborRow, neighborColumn, currentPath.buildPathWithNeighbor(grid, neighborRow, neighborColumn));
            }

            if(isCurrentResistanceLessThanLeastResistance(currentNeighborPath, leastResistanceNeighborPath)) {
                leastResistanceNeighborPath = currentNeighborPath;
            }
        }

        return leastResistanceNeighborPath;
    }

    private boolean isCurrentResistanceLessThanLeastResistance(ResistancePath currentPath, ResistancePath resistancePath) {
        return resistancePath == null || (currentIsLessThanLeast(currentPath, resistancePath) && resistancePath.canFlow());
    }

    private boolean currentIsLessThanLeast(ResistancePath currentPath, ResistancePath resistancePath) {
        return currentPath.getResistance() < resistancePath.getResistance();
    }

    private int next(int column) {
        return column + 1;
    }

    private boolean exists(Set<Integer> collection) {
        return !collection.isEmpty();
    }

    public LeastResistancePathFinder setGrid(Grid grid) {
        this.grid = grid;
        return this;
    }

}
