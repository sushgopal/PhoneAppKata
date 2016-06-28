package com.phoneappkata;


import com.phoneappkata.leastresistancepath.Grid;
import com.phoneappkata.leastresistancepath.ResistancePath;

import static java.lang.Integer.valueOf;

public class GridBuilder {

    public Grid buildFrom(int rows, int columns, EditTextAdapter adapter) {

        int[][] gridArray = new int[rows][columns];

        for(int i=0;i<rows;i++) {
            for(int j=0; j<columns; j++) {
                Object value = adapter.getItem((columns * i) + j);
                Integer intValue = valueOf(value.toString());
                gridArray[i][j] = intValue;
            }
        }

        return new Grid(gridArray);
    }

}
