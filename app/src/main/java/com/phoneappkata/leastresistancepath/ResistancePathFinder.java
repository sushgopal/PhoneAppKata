package com.phoneappkata.leastresistancepath;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Set;

import static com.phoneappkata.leastresistancepath.LeastResistancePath.NO_NEIGHBOR;

public class ResistancePathFinder {

    private Grid grid;

    private VisitedNodes visitedNodes = new VisitedNodes();

    public LeastResistancePath findAt(int row, int column, LeastResistancePath currentPath) {
        Set<Integer> neighborRows = grid.getNeighborRowsFor(row, column);
        LeastResistancePath leastResistanceNeighborPath = NO_NEIGHBOR;

        if(exists(neighborRows)) {
            return findLeastResistanceNeighbor(column, neighborRows, currentPath);
        }

       // LeastResistancePath path = new LeastResistancePath(grid, row, column, leastResistanceNeighborPath);
//        if((currentPath.getResistance()+grid.valueAt(row, column)) > 50) {
//            return new LeastResistancePath(currentPath.getResistance(), currentPath.getPath(), false);
//        }
//        List<Integer> list = Lists.newArrayList();
//        list.addAll(currentPath.getPath());
//        list.add(row+1);
//        LeastResistancePath path = new LeastResistancePath(
//                currentPath.getResistance()+grid.valueAt(row, column),
//                list,
//                true
//        );
        //visitedNodes.add(row, column, path);
        return currentPath;
    }

    private LeastResistancePath findLeastResistanceNeighbor(int column, Set<Integer> neighborRows, LeastResistancePath currentPath) {
        LeastResistancePath leastResistanceNeighborPath = null;
        int neighborColumn = next(column);

        for (Integer neighborRow : neighborRows) {
            LeastResistancePath currentNeighborPath;

//            if (visitedNodes.visited(neighborRow, neighborColumn)) {
//                currentNeighborPath = visitedNodes.get(neighborRow,  neighborColumn);
//            } else {
                if((currentPath.getResistance() + grid.valueAt(neighborRow, neighborColumn)) > 50) {
                    currentNeighborPath = new LeastResistancePath(currentPath.getResistance(), currentPath.getPath(), false);
                } else {
                    List<Integer> list = Lists.newArrayList();
                    list.addAll(currentPath.getPath());
                    list.add(neighborRow+1);
                    LeastResistancePath x = new LeastResistancePath(
                            currentPath.getResistance()+grid.valueAt(neighborRow, neighborColumn),
                            list, true
                    );
                    currentNeighborPath = findAt(neighborRow, neighborColumn, x);
                }

//            }

            if(isCurrentResistanceLessThanLeastResistance(currentNeighborPath, leastResistanceNeighborPath)) {
                leastResistanceNeighborPath = currentNeighborPath;
            }
        }

        return leastResistanceNeighborPath;
    }

    private boolean isCurrentResistanceLessThanLeastResistance(LeastResistancePath currentPath, LeastResistancePath leastResistancePath) {
        return leastResistancePath == null || (currentIsLessThanLeast(currentPath, leastResistancePath) && leastResistancePath.canFlow());
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
