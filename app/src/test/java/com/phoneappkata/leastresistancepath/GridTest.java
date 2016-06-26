package com.phoneappkata.leastresistancepath;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class GridTest {
    private Grid underTest;

    @Before
    public void setup() {
        underTest = new Grid(new int[][]{{1, 2},{3, 4},{5, 6}});
    }

    @Test
    public void shouldSetNumberOfRowsOfGrid() {
        assertThat(underTest.numberOfRows(), is(3));
    }

    @Test
    public void shouldSetNumberOfColumnsOfGrid() {
        assertThat(underTest.numberOfColumns(), is(2));
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

