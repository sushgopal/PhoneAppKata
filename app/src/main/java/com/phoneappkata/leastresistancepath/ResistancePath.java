package com.phoneappkata.leastresistancepath;

import com.google.common.collect.ImmutableList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ResistancePath {
    private static int MAX_RESISTANCE_TO_FLOW = 50;

    private static boolean CAN_FLOW = true;

    private static boolean CANNOT_FLOW = false;

    private int resistance;
    private List<Integer> path;
    private boolean canFlow;

    private ResistancePath(int resistance, List<Integer> path, boolean canFlow) {
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

    public ResistancePath buildPathWithNeighbor(Grid grid, int row, int column) {
        ImmutableList<Integer> list = ImmutableList.<Integer> builder()
                                                    .addAll(path)
                                                    .add(next(row))
                                                    .build();

        return new ResistancePath(getResistance()+grid.valueAt(row, column), list, CAN_FLOW);
    }

    private int next(int row) {
        return row + 1;
    }

    public ResistancePath buildBlockedPath() {
        return new ResistancePath(resistance, path, CANNOT_FLOW);
    }

    public boolean isNeighborBlockingFlow(Grid grid, int row, int column) {
        return total(grid.valueAt(row, column)) > MAX_RESISTANCE_TO_FLOW;
    }

    private int total(int neighborResistance) {
        return resistance + neighborResistance;
    }
}
