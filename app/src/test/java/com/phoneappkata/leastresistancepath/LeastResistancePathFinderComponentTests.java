package com.phoneappkata.leastresistancepath;

import com.google.common.collect.Lists;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class LeastResistancePathFinderComponentTests {

    Grid testGrid1 = new Grid(new int[][]{  {3, 4, 1, 2, 8, 6},
                                            {6, 1, 8, 2, 7, 4},
                                            {5, 9, 3, 9, 9, 5},
                                            {8, 4, 1, 3, 2, 6},
                                            {3, 7, 2, 8, 6, 4}
                                        });

    Grid testGrid2 = new Grid(new int[][]{  {3, 4, 1, 2, 8, 6},
                                            {6, 1, 8, 2, 7, 4},
                                            {5, 9, 3, 9, 9, 5},
                                            {8, 4, 1, 3, 2, 6},
                                            {3, 7, 2, 1, 2, 3}
                                        });

    Grid testGrid3 = new Grid(new int[][]{  {19, 10, 19, 10, 19},
                                            {21, 23, 20, 19, 12},
                                            {20, 12, 20, 11 ,10}
                                         });

    Grid testGrid4 = new Grid(new int[][]{  {-10, 15, 0, 3, 44},
                                            {50, 1, 2, -18, 9}
                                        });

    @Test
    public void shouldReturnExpectedPathForTestGrid1() {
        runTestAndAssertWith(testGrid1, 16, true, Lists.<Integer>newArrayList(1, 2, 3, 4, 4, 5));
    }

    @Test
    public void shouldReturnExpectedPathForTestGrid2() {
        runTestAndAssertWith(testGrid2, 11, true, Lists.<Integer>newArrayList(1, 2, 1, 5, 4, 5));
    }

    @Test
    public void shouldReturnExpectedPathForTestGrid3() {
        runTestAndAssertWith(testGrid3, 48, false, Lists.<Integer>newArrayList(1, 1, 1));
    }

    @Test
    public void shouldReturnExpectedPathForTestGrid4() {
        runTestAndAssertWith(testGrid4, -18, true, Lists.<Integer>newArrayList(1, 2, 1, 2, 2));
    }

    private void runTestAndAssertWith(Grid grid, int expectedResistance, boolean expectedCanFlow, List<Integer> expectedPath) {
        ResistancePath leastResistancePath = new LeastResistancePathFinder(grid).find();

        assertThat(leastResistancePath.getResistance(), is(expectedResistance));
        assertThat(leastResistancePath.getPath(), is(expectedPath));
        assertThat(leastResistancePath.canFlow(), is(expectedCanFlow));
    }

}
