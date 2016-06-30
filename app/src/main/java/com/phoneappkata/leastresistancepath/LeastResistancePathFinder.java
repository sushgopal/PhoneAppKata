package com.phoneappkata.leastresistancepath;

import com.google.common.collect.Iterables;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.phoneappkata.leastresistancepath.ResistancePath.MAX_RESISTANCE_TO_FLOW;

public class LeastResistancePathFinder {
    private Grid grid;

    private VisitedNodes visitedCells = new VisitedNodes();

    public LeastResistancePathFinder(Grid grid) {
        this.grid = grid;
    }

    public ResistancePath find() {
        ResistancePath leastResistancePath= findAt(grid.getRootRow(), grid.getRootColumn());

        if(leastResistancePath.isABlockedPath()) {
            return getBlockedResistancePath(leastResistancePath);
        }

        return leastResistancePath;
    }

    ResistancePath findAt(int row, int column) {
        Set<Integer> neighborRows = grid.getNeighborRowsFor(row, column);

        ResistancePath leastResistancePath;
        if(exists(neighborRows)) {
            leastResistancePath = findLeastResistanceNeighbor(row, column, neighborRows);
        }
        else {
            leastResistancePath = getResistancePath(row, column);
        }

        visitedCells.add(row, column, leastResistancePath);
        return leastResistancePath;
    }

    ResistancePath getResistancePath(int row, int column) {
        return new ResistancePath().buildPathWith(grid, row, column);
    }

    private ResistancePath findLeastResistanceNeighbor(int row, int column, Set<Integer> neighborRows) {
        int neighborColumn = grid.next(column);
        ResistancePath leastResistanceNeighborPath = null;

        for (Integer neighborRow : neighborRows) {
            ResistancePath currentNeighborPath;

            if(visitedCells.visited(neighborRow, neighborColumn)) {
                currentNeighborPath = visitedCells.get(neighborRow, neighborColumn);
            }
            else {
                currentNeighborPath = findAt(neighborRow, neighborColumn);
            }
            if (shouldUpdateLeastNeighborPath(currentNeighborPath, leastResistanceNeighborPath)) {
                leastResistanceNeighborPath = currentNeighborPath;
            }
        }

        return leastResistanceNeighborPath.buildPathWith(grid, row, column);
    }

    private boolean shouldUpdateLeastNeighborPath(ResistancePath currentPath, ResistancePath leastNeighborPath) {
        return  leastNeighborPath == null || (currentPath.getResistance() < leastNeighborPath.getResistance());
    }

    ResistancePath getBlockedResistancePath(ResistancePath leastResistancePath) {
        int resistance = 0;
        int column = 0;
        List<Integer> path = newArrayList();

        for(Integer row: leastResistancePath.getPath()) {
            if((resistance + grid.valueAt(row-1, column)) > MAX_RESISTANCE_TO_FLOW) {
                break;
            }
            resistance = resistance + grid.valueAt(row-1, column);
            path.add(row);
            column++;
        }

        return new ResistancePath(resistance, path, false);
    }

    private boolean exists(Set<Integer> collection) {
        return !collection.isEmpty();
    }

    public void setVisitedCells(VisitedNodes visitedCells) {
        this.visitedCells = visitedCells;
    }
}
