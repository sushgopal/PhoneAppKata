package com.phoneappkata.leastresistancepath;

import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.Set;

public class NeighborCells {
    private int[][] grid;

    public Set<Cell> findFor(Cell cell, int[][] grid) {
        this.grid = grid;

        if(isCellAtLastColumn(cell)) {
            return Collections.emptySet();
        }

        return Sets.newHashSet(getNeighborCellFromCurrentRow(cell),
                               getNeighborCellFromPreviousRow(cell),
                               getNeighborCellFromNextRow(cell));
    }

    private Cell getNeighborCellFromCurrentRow(Cell cell) {
        return new Cell(cell.getRow(), cell.getColumn()+1);
    }

    private Cell getNeighborCellFromPreviousRow(Cell cell) {
        int row = cell.getRow() == 0 ? numberOfRows()-1 : cell.getRow()-1;
        return new Cell(row, cell.getColumn()+1);
    }

    private Cell getNeighborCellFromNextRow(Cell cell) {
        int row = cell.getRow() == numberOfRows()-1 ? 0 : cell.getRow() + 1;
        return new Cell(row, cell.getColumn()+1);
    }

    private int numberOfColumns() {
        return grid[0].length;
    }

    private int numberOfRows() {
        return grid.length;
    }

    private boolean isCellAtLastColumn(Cell cell) {
        return (cell.getColumn() + 1) == numberOfColumns();
    }
}
