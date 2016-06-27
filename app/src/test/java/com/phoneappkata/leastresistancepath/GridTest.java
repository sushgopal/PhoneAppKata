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

    private int[][] gridArray = new int[][]{{1, 2},{3, 4},{5, 6}};

    private int rowCount = gridArray.length;

    private int columnCount = gridArray[0].length;

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
        assertThat(underTest.getNeighborRowsFor(root, root), contains(0, 1, 2));
    }

    @Test
    public void shouldReturnNoNeighborsIfColumnIsTheLastOne() {
        assertThat(neighborRowsFor(0, 1), is(empty()));
    }

    @Test
    public void shouldContainPreviousRowNumber() {
        assertThat(neighborRowsFor(1, 0), hasItem(0));
    }

    @Test
    public void shouldContainFirstRowNumberIfInputRowIsTheLastOne() {
        assertThat(neighborRowsFor(2, 0), hasItem(0));
    }

    @Test
    public void shouldContainSameRowNumber() {
        assertThat(neighborRowsFor(1, 0), hasItem(1));
    }

    @Test
    public void shouldContainNextRowNumber() {
        assertThat(neighborRowsFor(1, 0), hasItem(2));
    }

    @Test
    public void shouldContainLastRowNumberIfInputRowIsTheFirstOne() {
        assertThat(neighborRowsFor(0, 0), hasItem(2));
    }

    private Set<Integer> neighborRowsFor(int row, int column) {
        return underTest.getNeighborRowsFor(row, column);
    }

    @Test
    public void shouldNotContainSameRowNumberTwice() {
        underTest = new Grid(new int[][]{{0, 0}, {1, 1}});

        assertThat(neighborRowsFor(0, 0).size(), is(2));
    }

    @Test
    public void shouldReturnArrayValueAtRowAndColumn() {
        assertThat(underTest.valueAt(1, 0), is(3));
    }
}

