package com.phoneappkata.leastresistancepath;

import com.google.common.collect.Lists;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class LeastResistancePathFinderComponentTests {
    LeastResistancePathFinder finder = new LeastResistancePathFinder();

    Grid testGrid1 = new Grid(new int[][]{{3, 4, 1, 2, 8, 6}, {6, 1, 8, 2, 7, 4}, {5, 9, 3, 9, 9, 5}, { 8, 4, 1, 3, 2, 6}, {3, 7, 2, 8, 6, 4}});

    Grid testGrid2 = new Grid(new int[][]{{3, 4, 1, 2, 8, 6}, {6, 1, 8, 2, 7, 4}, {5, 9, 3, 9, 9, 5}, { 8, 4, 1, 3, 2, 6}, {3, 7, 2, 1, 2, 3}});

    Grid testGrid3 = new Grid(new int[][]{{19, 10, 19, 10, 19}, {21, 23, 20, 19, 12},{20, 12, 20, 11 ,10}});

    @Test
    public void testCase1() {
        runTestAndAssertWith(testGrid1, 16, true, Lists.<Integer>newArrayList(1, 2, 3, 4, 4, 5));
    }

    @Test
    public void testCase2() {
        runTestAndAssertWith(testGrid2, 11, true, Lists.<Integer>newArrayList(1, 2, 1, 5, 4, 5));
    }

    @Test
    public void testCase3() {
        runTestAndAssertWith(testGrid3, 48, false, Lists.<Integer>newArrayList(1, 1, 1));
    }

    private void runTestAndAssertWith(Grid grid, int expectedResistance, boolean expectedCanFlow, List<Integer> expectedPath) {
        ResistancePath leastResistancePath =  finder.find(grid);
        assertThat(leastResistancePath.getResistance(), is(expectedResistance));
        assertThat(leastResistancePath.getPath(), is(expectedPath));
        assertThat(leastResistancePath.canFlow(), is(expectedCanFlow));
    }

}
