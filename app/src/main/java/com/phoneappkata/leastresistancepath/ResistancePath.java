package com.phoneappkata.leastresistancepath;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ResistancePath {
    static int MAX_RESISTANCE_TO_FLOW = 50;

    private static boolean CAN_FLOW = true;

    private int resistance;
    private List<Integer> path;
    private boolean canFlow;

    ResistancePath(int resistance, List<Integer> path, boolean canFlow) {
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

    public ResistancePath buildPathWith(Grid grid, int row, int column) {
        List<Integer> list = newArrayList();
        if(!grid.isRoot(row, column)) {
            list.add(grid.next(row));
        }
        list.addAll(path);

        return new ResistancePath(total(grid.valueAt(row, column)), list, CAN_FLOW);
    }

    private int total(int neighborResistance) {
        return resistance + neighborResistance;
    }

    public boolean isABlockedPath() {
        return resistance > MAX_RESISTANCE_TO_FLOW;
    }
}
