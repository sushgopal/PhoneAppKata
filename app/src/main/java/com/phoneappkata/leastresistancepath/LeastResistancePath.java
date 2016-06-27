package com.phoneappkata.leastresistancepath;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class LeastResistancePath {
    static LeastResistancePath NO_NEIGHBOR = null;

    private static int MAX_RESISTANCE_TO_FLOW = 50;

    private int resistance;
    private List<Integer> path;
    private boolean canFlow;

    public LeastResistancePath(int resistance, List<Integer> path, boolean canFlow) {
        this.resistance = resistance;
        this.path = path;
        this.canFlow = canFlow;
    }

    public LeastResistancePath(Grid grid, int row, int column) {
        this(grid, row, column, NO_NEIGHBOR);
    }

    public LeastResistancePath(Grid grid, int row, int column, LeastResistancePath leastResistanceNeighbor) {
        int totalResistance = getTotalResistance(grid, row, column, leastResistanceNeighbor);

        this.canFlow = canFlow(totalResistance);
        this.path = getPath(row, leastResistanceNeighbor);
        this.resistance = getResistance(grid, row, column, totalResistance);
    }

    public int getResistance() {
        return resistance;
    }

    private int getResistance(Grid grid, int row, int column, int totalResistance) {
        return canFlow ? totalResistance : grid.valueAt(row, column);
    }

    public List<Integer> getPath() {
        return path;
    }

    private Integer getPath(int row) {
        return next(row);
    }

    private List<Integer> getPath(int row, LeastResistancePath leastResistanceNeighbor) {
        List<Integer> path = newArrayList(getPath(row));
        if(leastResistanceNeighbor != null && canFlow) {
            path.addAll(leastResistanceNeighbor.getPath());
        }
        return path;
    }

    public boolean canFlow() {
        return canFlow;
    }

    private boolean canFlow(int resistance) {
        return resistance <= MAX_RESISTANCE_TO_FLOW;
    }

    private int next(int row) {
        return row + 1;
    }

    private int getTotalResistance(Grid grid, int row, int column, LeastResistancePath leastResistanceNeighbor) {
        int totalResistance = grid.valueAt(row, column);
        if(leastResistanceNeighbor != null) {
            totalResistance += leastResistanceNeighbor.getResistance();
        }
        return totalResistance;
    }


}
