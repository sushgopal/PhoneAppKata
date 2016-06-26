package com.phoneappkata.leastresistancepath;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.emptySet;

public class Grid {

    private static final int FIRST_ROW_NUMBER = 0;
    private int[][] grid;

    public Grid(int[][] grid) {
        this.grid = grid;
    }


    public int numberOfRows() {
        return grid.length;
    }

    public int numberOfColumns() {
        return grid[0].length;
    }

    public Set<Integer> getNeighborRowsFor(int row, int column) {
        if(isLastColumn(column)) {
            return emptySet();
        }
        return newHashSet(getRowNumberPreviousTo(row), row, getRowNumberNextTo(row));
    }

    private int getRowNumberNextTo(int row) {
        return isLastRow(row) ? FIRST_ROW_NUMBER : (row + 1);
    }

    private int getRowNumberPreviousTo(int row) {
        return isFirstRow(row) ? lastRowNumber() : (row - 1);
    }

    private boolean isFirstRow(int row) {
        return row == FIRST_ROW_NUMBER;
    }

    private int lastRowNumber() {
        return numberOfRows() - 1;
    }

    private boolean isLastRow(int row) {
        return numberOfRows() == (row + 1);
    }

    private boolean isLastColumn(int column) {
        return numberOfColumns() == (column + 1);
    }

    public int valueAt(int row, int column) {
        return grid[row][column];
    }
}
