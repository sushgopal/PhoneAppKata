package com.phoneappkata.leastresistancepath;

import org.hamcrest.collection.IsIterableWithSize;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class NeighborCellsTest {
    private NeighborCells underTest;
    private int[][] grid;

    @Before
    public void setup() {
        underTest = new NeighborCells();

        grid = new int[][]{{1, 2, 3},{4, 5, 6},{7, 8, 9}};
    }

    @Test
    public void shouldReturnEmptyListIfCurrentCellIsInLastColumn() {
        Set<Cell> result = runTestForCell(new Cell(0, 2), grid);

        assertThat(result, is(empty()));
    }

    @Test
    public void shouldReturnCellInSameRowAndColumnToTheRight() {
        Set<Cell> result = runTestForCell(new Cell(1, 0), grid);

        assertResultToBe(result, new Cell(1, 1));
    }

    @Test
    public void shouldReturnCellInPreviousRowAndColumnToTheRight() {
        Set<Cell> result = runTestForCell(new Cell(1, 0), grid);

        assertResultToBe(result, new Cell(0, 1));
    }

    @Test
    public void shouldReturnCellInNextRowAndColumnToTheRight() {
        Set<Cell> result = runTestForCell(new Cell(1, 0), grid);

        assertResultToBe(result, new Cell(2, 1));
    }

    @Test
    public void shouldReturnCellInLastRowAndColumnAtRightWhenInputCellIsAtFirstRow() {
        Set<Cell> result = runTestForCell(new Cell(0, 0), grid);

        assertResultToBe(result, new Cell(2, 1));
    }

    @Test
    public void shouldReturnTheCellInFirstRowAndColumnAtRightIfCurentCellIsAtLastRow() {
        Set<Cell> result = runTestForCell(new Cell(2, 0), grid);

        assertResultToBe(result, new Cell(0, 1));
    }

    @Test
    public void shouldNotContainDuplicateCells() {
        grid = new int[][]{{1, 2, 3}};

        Set<Cell> result = runTestForCell(new Cell(0, 0), grid);

        assertThat(result, IsIterableWithSize.<Cell> iterableWithSize(1));
    }

    private void assertResultToBe(Set<Cell> actual, Cell expected) {
        assertThat(actual, hasItem(expected));
    }

    private Set<Cell> runTestForCell(Cell cell, int[][] grid) {
        return underTest.findFor(cell, grid);
    }
}
