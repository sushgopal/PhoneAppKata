package com.phoneappkata.leastresistancepath;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ResistancePath {
    private static int MAX_RESISTANCE_TO_FLOW = 50;

    private int resistance;
    private List<Integer> path;
    private boolean canFlow;

    public ResistancePath(int resistance, List<Integer> path, boolean canFlow) {
        this.resistance = resistance;
        this.path = path;
        this.canFlow = canFlow;
    }

    public ResistancePath() {
        this.resistance = 0;
        this.canFlow = false;
        this.path = newArrayList();
    }

    public int getResistance() {
        return resistance;
    }

    public List<Integer> getPath() {
        return path;
    }

    public boolean canFlow() {
        return canFlow;
    }

    public boolean isNeighborBlockingFlow(Grid grid, int row, int column) {
        return (resistance + grid.valueAt(row, column)) > MAX_RESISTANCE_TO_FLOW;
    }

    public ResistancePath buildPathWithNeighbor(Grid grid, int row, int column) {
        ArrayList<Integer> list = Lists.newArrayList();
        list.addAll(getPath());
        list.add(row+1);
        return new ResistancePath(getResistance()+grid.valueAt(row, column), list, true);
    }

    public ResistancePath buildBlockedPath() {
        return new ResistancePath(getResistance(), getPath(), false);
    }

}
