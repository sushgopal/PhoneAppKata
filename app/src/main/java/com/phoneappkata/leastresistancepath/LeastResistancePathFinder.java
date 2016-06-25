package com.phoneappkata.leastresistancepath;

import java.util.Collections;
import java.util.List;

import static java.lang.String.valueOf;

public class LeastResistancePathFinder {

    public LeastResistancePath find(int[][] grid) {

        LeastResistancePath result = null;

        for(int i=0;i<grid.length;i++) {
            LeastResistancePath temp = findAt(i, 0, grid);
            if(result == null || temp.getResistance() < result.getResistance()) {
                result = temp;
            }
        }

        return result;
    }

    LeastResistancePath findAt(int row, int column, int[][] grid) {
        List<Cell> neighborCells = findPossibleNeighborCellsToFlow(row, column);

        LeastResistancePath result = new LeastResistancePath(grid[row][column], valueOf(row));
        for(Cell cell: neighborCells) {
            LeastResistancePath temp = findAt(cell.getRow(), cell.getColumn(), grid);
            if(temp.getResistance() < result.getResistance()) {
                result = temp;
            }
        }

        return result;
    }

    List<Cell> findPossibleNeighborCellsToFlow(int row, int column) {
        return Collections.emptyList();
    }
}
