package com.phoneappkata.leastresistancepath;

import java.util.HashMap;

public class VisitedNodes extends HashMap<String, LeastResistancePath> {

    private static String KEY_SEPARATOR = ":";

    public void add(int row, int column, LeastResistancePath path) {
        put(getKey(row, column),  path);
    }

    private String getKey(int row, int column) {
        return row + KEY_SEPARATOR + column;
    }

    public boolean visited(int row, int column) {
        return containsKey(getKey(row, column));
    }

    public LeastResistancePath get(int row, int column) {
        return get(getKey(row, column));
    }
}
