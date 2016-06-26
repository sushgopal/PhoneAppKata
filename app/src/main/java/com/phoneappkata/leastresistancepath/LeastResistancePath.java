package com.phoneappkata.leastresistancepath;

public class LeastResistancePath {
    static LeastResistancePath NO_NEIGHBOR = null;

    private static int MAX_RESISTANCE_TO_FLOW = 50;

    private static String PATH_DELIMITER = " ";

    private int resistance;
    private String path;
    private boolean canFlow;

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

    public String getPath() {
        return path;
    }

    private String getPath(int row) {
        return next(row);
    }

    private String getPath(int row, LeastResistancePath leastResistanceNeighbor) {
        String path = getPath(row);
        if(leastResistanceNeighbor != null && canFlow) {
            path = path + PATH_DELIMITER + leastResistanceNeighbor.getPath();
        }
        return path;
    }

    public boolean canFlow() {
        return canFlow;
    }

    private boolean canFlow(int resistance) {
        return resistance <= MAX_RESISTANCE_TO_FLOW;
    }

    private String next(int row) {
        return Integer.toString((row + 1));
    }

    private int getTotalResistance(Grid grid, int row, int column, LeastResistancePath leastResistanceNeighbor) {
        int totalResistance = grid.valueAt(row, column);
        if(leastResistanceNeighbor != null) {
            totalResistance += leastResistanceNeighbor.getResistance();
        }
        return totalResistance;
    }


}
