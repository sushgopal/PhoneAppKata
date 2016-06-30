package com.phoneappkata.leastresistancepath;

import android.support.annotation.NonNull;

import com.google.common.collect.ContiguousSet;

import java.util.Set;

import static com.google.common.collect.ContiguousSet.create;
import static com.google.common.collect.DiscreteDomain.integers;
import static com.google.common.collect.Range.closedOpen;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.emptySet;

public class Grid {

    private static int FIRST_ROW_NUMBER = 0;
    private static int ROOT = -1;

    private int[][] grid;

    public Grid(int rows, int columns) {
        this.grid = new int[rows][columns];
    }

    Grid (int[][] grid) {
        this.grid = grid;
    }

    public void setAt(int row, int column, int value) {
        grid[row][column]= value;
    }

    public int numberOfRows() {
        return grid.length;
    }

    public int numberOfColumns() {
        return grid[0].length;
    }

    public Set<Integer> getNeighborRowsFor(int row, int column) {
        if(isRoot(row, column)) {
            return getRowIndices();
        }
        if(isLastColumn(column)) {
            return emptySet();
        }
        return newHashSet(getRowNumberPreviousTo(row), row, getRowNumberNextTo(row));
    }

    public int valueAt(int row, int column) {
        if(row == -1 || column == -1) {
            return 0;
        }
        return grid[row][column];
    }

    public int getRootRow() {
        return ROOT;
    }

    public int getRootColumn() {
        return ROOT;
    }

    public int next(int index) {
        return index + 1;
    }

    @NonNull
    private ContiguousSet<Integer> getRowIndices() {
        return create(closedOpen(FIRST_ROW_NUMBER, numberOfRows()), integers());
    }

    boolean isRoot(int row, int column) {
        return (row == column)  &&(row == ROOT);
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

    public int previous(Integer index) {
        return index -1;
    }
}
