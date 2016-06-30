package com.phoneappkata.leastresistancepath;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class GridTest {
    private Grid underTest;

    private int root = -1;

    private int resitance = 3;

    private int[][] gridArray = new int[][]{{1, 2},{resitance, 4},{5, 6}};

    private int rowCount = gridArray.length;

    private int columnCount = gridArray[0].length;

    private int lastColumn = columnCount - 1;

    private int column = 0;

    private int firstRow = 0;

    private int secondRow = 1;

    private int thirdRow = 2;

    @Before
    public void setup() {
        underTest = new Grid(gridArray);
    }

    @Test
    public void shouldReturnNumberOfRowsOfGrid() {
        assertThat(underTest.numberOfRows(), is(rowCount));
    }

    @Test
    public void shouldReturnNumberOfColumnsOfGrid() {
        assertThat(underTest.numberOfColumns(), is(columnCount));
    }

    @Test
    public void shouldReturnRootRow() {
        assertThat(underTest.getRootRow(), is(root));
    }

    @Test
    public void shouldReturnRootColumn() {
        assertThat(underTest.getRootColumn(), is(root));
    }

    @Test
    public void shouldReturnRowIndicesAsRootNeighbors() {
        assertThat(underTest.getNeighborRowsFor(root, root), contains(firstRow, secondRow, thirdRow));
    }

    @Test
    public void shouldReturnNoNeighborsIfColumnIsLast() {
        assertThat(neighborRowsFor(firstRow, lastColumn), is(empty()));
    }

    @Test
    public void shouldAddPreviousRowNumberToNeighbors() {
        assertThat(neighborRowsFor(secondRow, column), hasItem(firstRow));
    }

    @Test
    public void shouldAddFirstRowNumberToNieghborsIfInputRowIsLast() {
        assertThat(neighborRowsFor(secondRow, column), hasItem(firstRow));
    }

    @Test
    public void shouldAddSameRowNumberToNeighbors() {
        assertThat(neighborRowsFor(secondRow, column), hasItem(secondRow));
    }

    @Test
    public void shouldAddNextRowNumberToNeighbors() {
        assertThat(neighborRowsFor(secondRow, column), hasItem(thirdRow));
    }

    @Test
    public void shouldAddLastRowNumberToNeighborsIfInputRowIsFirst() {
        assertThat(neighborRowsFor(firstRow, column), hasItem(thirdRow));
    }

    private Set<Integer> neighborRowsFor(int row, int column) {
        return underTest.getNeighborRowsFor(row, column);
    }

    @Test
    public void shouldNotContainSameRowNumberTwice() {
        underTest = new Grid(new int[][]{{0, 0}, {1, 1}});

        assertThat(neighborRowsFor(firstRow, column).size(), is(2));
    }

    @Test
    public void shouldReturnArrayValueAtRowAndColumn() {
        assertThat(underTest.valueAt(secondRow, column), is(resitance));
    }

    @Test
    public void shouldReturnArrayValueAtRoot() {
        assertThat(underTest.valueAt(root, root), is(0));
    }

    @Test
    public void shouldReturnNextIndex() {
        assertThat(underTest.next(secondRow), is(thirdRow));
    }

    @Test
    public void shouldReturnPreviousIndex() {
        assertThat(underTest.previous(secondRow), is(firstRow));
    }

    @Test
    public void shouldSetValue() {
        underTest.setAt(firstRow, column, resitance);

        assertThat(underTest.valueAt(firstRow, column), is(resitance));
    }

    @Test
    public void shouldReturnTrueIfRowAndColumnAreRoot() {
        assertThat(underTest.isRoot(root, root), is(true));
    }

    @Test
    public void shouldReturnFalseIfRowAndColumnAreNotRoot() {
        assertThat(underTest.isRoot(firstRow, column), is(false));
    }
}

