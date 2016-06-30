package com.phoneappkata.leastresistancepath;

import android.support.annotation.NonNull;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.phoneappkata.leastresistancepath.ResistancePath.MAX_RESISTANCE_TO_FLOW;

public class LeastResistancePathFinder2 {
    private Grid grid;

    private VisitedNodes visitedCells = new VisitedNodes();

    public LeastResistancePathFinder2(Grid grid) {
        this.grid = grid;
    }

    public ResistancePath find() {
        ResistancePath leastResistancePath= findAt(grid.getRootRow(), grid.getRootColumn());
        leastResistancePath.getPath().remove(0);

        if(leastResistancePath.isABlockedPath()) {
            return getBlockedResistancePath(leastResistancePath);
        }

        return leastResistancePath;
    }

    @NonNull
    private ResistancePath getBlockedResistancePath(ResistancePath leastResistancePath) {
        int blockedResistance = 0;
        List<Integer> blockedPath = newArrayList();
        int column = 0;
        for(Integer row: leastResistancePath.getPath()) {

            if((blockedResistance + grid.valueAt(row-1, column)) > MAX_RESISTANCE_TO_FLOW) {
                break;
            }
            blockedResistance = blockedResistance + grid.valueAt(row-1, column);
            blockedPath.add(row);
            column++;
        }

        return new ResistancePath(blockedResistance, blockedPath, false);
    }

    ResistancePath findAt(int row, int column) {
        Set<Integer> neighborRows = grid.getNeighborRowsFor(row, column);

        ResistancePath leastResistancePath;
        if(exists(neighborRows)) {
            leastResistancePath = findLeastResistanceNeighbor(row, column, neighborRows);
        }
        else {
            leastResistancePath = new ResistancePath().buildPathWithNeighbor(grid, row, column);
        }

        visitedCells.add(row, column, leastResistancePath);
        return leastResistancePath;
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

        return leastResistanceNeighborPath.buildPathWithNeighbor(grid, row, column);
    }

    private boolean shouldUpdateLeastNeighborPath(ResistancePath currentPath, ResistancePath leastNeighborPath) {
        return  leastNeighborPath == null || (currentPath.getResistance() < leastNeighborPath.getResistance());
    }

    private boolean exists(Set<Integer> collection) {
        return !collection.isEmpty();
    }

    private class VisitedNodes extends HashMap<String, ResistancePath> {

        private String KEY_SEPARATOR = ":";

        public void add(int row, int column, ResistancePath path) {
            put(getKey(row, column),  path);
        }

        private String getKey(int row, int column) {
            return row + KEY_SEPARATOR + column;
        }

        public boolean visited(int row, int column) {
            return containsKey(getKey(row, column));
        }

        public ResistancePath get(int row, int column) {
            return get(getKey(row, column));
        }
    }
}
