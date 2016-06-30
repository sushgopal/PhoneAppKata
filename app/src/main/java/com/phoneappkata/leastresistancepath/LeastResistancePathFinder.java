package com.phoneappkata.leastresistancepath;

import java.util.Set;

public class LeastResistancePathFinder {

    private Grid grid;

    public LeastResistancePathFinder(Grid grid) {
        this.grid = grid;
    }

    public ResistancePath find() {
        return findAt(grid.getRootRow(), grid.getRootColumn(), getNewPath());
    }

    ResistancePath getNewPath() {
        return new ResistancePath();
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
        int neighborColumn = grid.next(column);

        for (Integer neighborRow : neighborRows) {
            ResistancePath currentNeighborPath;

//            if(currentPath.isNeighborBlockingFlow(grid, neighborRow, neighborColumn)) {
//                currentNeighborPath = currentPath.buildBlockedPath();
//            } else {
//                currentNeighborPath = findAt(neighborRow, neighborColumn, getPath(currentPath, neighborRow, neighborColumn));
//            }
//
//            if(shouldUpdateLeastNeighborPath(currentNeighborPath, leastResistanceNeighborPath)) {
//                leastResistanceNeighborPath = currentNeighborPath;
//            }
        }

        return leastResistanceNeighborPath;
    }

    private ResistancePath getPath(ResistancePath currentPath, int neighborRow, int neighborColumn) {
        return currentPath.buildPathWithNeighbor(grid, neighborRow, neighborColumn);
    }

    private boolean shouldUpdateLeastNeighborPath(ResistancePath currentPath, ResistancePath previousPath) {
        return  previousPath == null || (isCurrentLessThanPreviousResistance(currentPath, previousPath) && currentPath.canFlow());
    }

    private boolean isCurrentLessThanPreviousResistance(ResistancePath currentPath, ResistancePath previousPath) {
        return currentPath.getResistance() < previousPath.getResistance();
    }

    private boolean exists(Set<Integer> collection) {
        return !collection.isEmpty();
    }

}
