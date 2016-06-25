package com.phoneappkata.leastresistancepath;

public class LeastResistancePath {
    private int resistance;
    private String path;

    public LeastResistancePath(int resistance, String path) {
        this.resistance = resistance;
        this.path = path;
    }

    public int getResistance() {
        return resistance;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "LeastResistancePath{" +
                "resistance=" + resistance +
                ", path='" + path + '\'' +
                '}';
    }
}
