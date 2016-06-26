package com.phoneappkata.leastresistancepath;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class Realtest {
    LeastResistancePathFinder finder = new LeastResistancePathFinder();

    @Test
    public void test() {
        int[][] grid = new int[][]{{3, 4, 1, 2, 8, 6}, {6, 1, 8, 2, 7, 4}, {5, 9, 3, 9, 9, 5}, { 8, 4, 1, 3, 2, 6}, {3, 7, 2, 1, 2, 3}};
//        int[][] grid = new int[][]{{19,10,19, 10, 19}, { 21 ,23, 20 ,19, 12} ,{ 20, 12, 20, 11 ,10}};
        LeastResistancePath r =  finder.find(grid);
        assertThat(r.getResistance(), is(11));
        assertThat(r.getPath(), is("1 2 1 5 4 5"));
    }

}
