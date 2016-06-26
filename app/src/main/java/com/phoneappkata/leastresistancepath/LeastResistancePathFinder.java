package com.phoneappkata.leastresistancepath;

public class LeastResistancePathFinder {

    private Grid grid;

    private ResistancePathFinder finder;

    public LeastResistancePath find(int[][] gridArray) {
        grid = getGrid(gridArray);
        finder = getFinder(grid);

        LeastResistancePath result = null;

        for(int i=0;i<grid.numberOfRows();i++) {
            LeastResistancePath temp = finder.findAt(i, 0);

            if(result == null || (temp.getResistance() < result.getResistance())) {
                result = temp;
            }
        }

        return result;
    }

    ResistancePathFinder getFinder(Grid grid) {
        return new ResistancePathFinder(grid);
    }

    Grid getGrid(int[][] gridArray) {
        return new Grid(gridArray);
    }

}
