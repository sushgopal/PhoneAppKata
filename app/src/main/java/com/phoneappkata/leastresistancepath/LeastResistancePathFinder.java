package com.phoneappkata.leastresistancepath;

import com.google.common.collect.Lists;

public class LeastResistancePathFinder {

    private Grid grid;

    private ResistancePathFinder finder;

    public LeastResistancePath find(int[][] gridArray) {
        grid = getGrid(gridArray);
        finder = getFinder(grid);

        LeastResistancePath result = null;

        for(int i=0;i<grid.numberOfRows();i++) {
            LeastResistancePath x = new LeastResistancePath(grid.valueAt(i, 0), Lists.newArrayList(i+1), true);
            LeastResistancePath temp = finder.findAt(i, 0, x);

            if(result == null || (temp.getResistance() < result.getResistance())) {
                result = temp;
            }
        }

        return result;
    }

    ResistancePathFinder getFinder(Grid grid) {
        return new ResistancePathFinder().setGrid(grid);
    }

    Grid getGrid(int[][] gridArray) {
        return new Grid(gridArray);
    }

}
