package com.phoneappkata.leastresistancepath;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Set;

import static com.phoneappkata.leastresistancepath.ResistancePath.NO_NEIGHBOR;

public class ResistancePathFinder {

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

            if((currentPath.getResistance() + grid.valueAt(neighborRow, neighborColumn)) > 50) {
                currentNeighborPath = new ResistancePath(currentPath.getResistance(), currentPath.getPath(), false);
            } else {
                List<Integer> list = Lists.newArrayList();
                list.addAll(currentPath.getPath());
                list.add(neighborRow+1);
                ResistancePath x = new ResistancePath(
                        currentPath.getResistance() + grid.valueAt(neighborRow, neighborColumn),
                        list, true);
                currentNeighborPath = findAt(neighborRow, neighborColumn, x);
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

    public ResistancePathFinder setGrid(Grid grid) {
        this.grid = grid;
        return this;
    }

}
