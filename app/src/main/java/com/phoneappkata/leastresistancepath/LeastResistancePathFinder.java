package com.phoneappkata.leastresistancepath;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Set;

import static java.lang.String.valueOf;

public class LeastResistancePathFinder {

    private NeighborCells neighborCells = new NeighborCells();

    private Map<String, LeastResistancePath> visitedNodeMap = Maps.newHashMap();

    public LeastResistancePath find(int[][] grid) {

        LeastResistancePath result = null;

        for(int i=0;i<grid.length;i++) {
            LeastResistancePath temp = findAt(i, 0, grid, null);
            if(result == null || (temp.getResistance() < result.getResistance())) {
                result = temp;
            }
        }

        return result;
    }

    LeastResistancePath findAt(int row, int column, int[][] grid, LeastResistancePath result) {
        Set<Cell> neighborCells = findPossibleNeighborCellsToFlow(row, column, grid);

        if(neighborCells.isEmpty()) {
            LeastResistancePath leaf = new LeastResistancePath(grid[row][column], valueOf(row+1));
            visitedNodeMap.put(""+row+column,  leaf);
            return leaf;
        }

        for(Cell cell: neighborCells) {
            LeastResistancePath temp = visitedNodeMap.get(cell.getRow()+""+cell.getColumn()) == null ?
                                        findAt(cell.getRow(), cell.getColumn(), grid, result):
                                        visitedNodeMap.get(cell.getRow()+""+cell.getColumn());
            if( result == null || (temp.getResistance() < result.getResistance())) {
                result = temp;
            }
        }
        result = new LeastResistancePath(grid[row][column]+result.getResistance(), valueOf(row+1)+","+result.getPath());
        visitedNodeMap.put(""+row+column,  result);
        return result;
    }

    private Set<Cell> findPossibleNeighborCellsToFlow(int row, int column, int[][] grid) {
        return neighborCells.findFor(new Cell(row, column), grid);
    }
}
