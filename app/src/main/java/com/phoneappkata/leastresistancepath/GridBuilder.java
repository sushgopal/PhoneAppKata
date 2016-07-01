package com.phoneappkata.leastresistancepath;

import com.phoneappkata.activity.EditTextAdapter;

import static java.lang.Integer.valueOf;

public class GridBuilder {

    public Grid buildFrom(final int rows, final int columns, final EditTextAdapter adapter) {

        Grid grid = new Grid(rows, columns);

        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                Object value = getItem(columns, adapter, i, j);
                Integer intValue = valueOf(value.toString());
                grid.setAt(i, j, intValue);
            }
        }

        return grid;
    }

    private Object getItem(int columns, EditTextAdapter adapter, int row, int column) {
        return adapter.getItem((columns * row) + column);
    }

}
