package com.phoneappkata.leastresistancepath;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Set;

import static java.lang.String.valueOf;

public class ResistancePathFinder {

    private Grid grid;

    Map<String, LeastResistancePath> visitedNodes = Maps.newHashMap();

    private static int MAX_RESISTANCE_TO_FLOW = 50;

    public ResistancePathFinder(Grid grid) {
        this.grid = grid;
    }

    private boolean isEmpty(Set<Integer> collection) {
        return collection.isEmpty();
    }

    public LeastResistancePath findAt(int row, int column) {
        Set<Integer> neighborRows = grid.getNeighborRowsFor(row, column);

        if(isEmpty(neighborRows)) {
            return leastResistancePathAsItself(row, column);
        }
        else {
            LeastResistancePath leastResistanceNeighbor = findLeastResistanceNeighbor(column, neighborRows);

            return leastResistancePathFrom(row, column, leastResistanceNeighbor);
        }
    }

    private LeastResistancePath leastResistancePathAsItself(int row, int column) {
        int resistance = grid.valueAt(row, column);
        String path = valueOf(next(row));
        boolean canFlow = lessThanEqualToMaxResistanceFlow(grid.valueAt(row, column));

        LeastResistancePath pathToItSelf = new LeastResistancePath(resistance, path, canFlow);
        visitedNodes.put(getKey(row, column),  pathToItSelf);

        return pathToItSelf;
    }

    private LeastResistancePath findLeastResistanceNeighbor(int column, Set<Integer> neighborRows) {
        LeastResistancePath leastResistanceNeighbor = null;

        for (Integer neighborRow : neighborRows) {
            LeastResistancePath currentNeighbor;
            if (rowAndColumnAlreadyVisited(neighborRow, next(column))) {
                currentNeighbor = visitedNodes.get(getKey(neighborRow, (next(column))));
            } else {
                currentNeighbor = findAt(neighborRow, (next(column)));
            }
            if(isCurrentResistanceLessThanLeastResistance(currentNeighbor, leastResistanceNeighbor)) {
                leastResistanceNeighbor = currentNeighbor;
            }
        }

        return leastResistanceNeighbor;
    }

    private LeastResistancePath leastResistancePathFrom(int row, int column, LeastResistancePath leastResistanceNeighbor) {

        int currentResistance = grid.valueAt(row, column);

        if(isNeighborBlockingFlow(currentResistance, leastResistanceNeighbor.getResistance())) {
            return new LeastResistancePath(currentResistance, valueOf(next(row)), false);
        }
        else {
            return new LeastResistancePath((currentResistance + leastResistanceNeighbor.getResistance()),
                                            (valueOf(next(row)) + " " + leastResistanceNeighbor.getPath()),
                                            true);
        }
    }

    private boolean isCurrentResistanceLessThanLeastResistance(LeastResistancePath currentPath, LeastResistancePath leastResistancePath) {
        return leastResistancePath == null || currentIsLessThanLeast(currentPath, leastResistancePath);
    }

    private boolean currentIsLessThanLeast(LeastResistancePath currentPath, LeastResistancePath leastResistancePath) {
        return currentPath.getResistance() < leastResistancePath.getResistance();
    }

    private boolean isNeighborBlockingFlow(int currentResistance, int leastResistance) {
        return (currentResistance + leastResistance) > MAX_RESISTANCE_TO_FLOW;
    }

    private int next(int column) {
        return column + 1;
    }

    private boolean rowAndColumnAlreadyVisited(int row, int column) {
        return visitedNodes.containsKey(getKey(row, column));
    }

    private String getKey(int row, int column) {
        return row+":"+column;
    }

    private boolean lessThanEqualToMaxResistanceFlow(int resistance) {
        return resistance <= MAX_RESISTANCE_TO_FLOW;
    }
}
