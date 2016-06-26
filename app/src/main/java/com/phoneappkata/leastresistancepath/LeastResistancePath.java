package com.phoneappkata.leastresistancepath;

public class LeastResistancePath {
    private int resistance;
    private String path;
    private boolean canFlow;

    public LeastResistancePath(int resistance, String path, boolean canFlow) {
        this.resistance = resistance;
        this.path = path;
        this.canFlow = canFlow;
    }

    public int getResistance() {
        return resistance;
    }

    public String getPath() {
        return path;
    }

    public boolean canFlow() {
        return canFlow;
    }
}
