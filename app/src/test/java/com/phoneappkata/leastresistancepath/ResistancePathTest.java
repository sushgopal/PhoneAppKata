package com.phoneappkata.leastresistancepath;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ResistancePathTest {

    private ResistancePath underTest;

    @Mock
    private Grid grid;

    private int row = 0;

    private int column = 0;

    private int noResistance = 0;

    private int resistance = 4;

    private int nextRow = 1;

    private int neighborResistance = 10;

    private int largerNeighborResistance = 48;

    private int neighborRow = 0;

    private int neighborColumn = 1;

    @Before
    public void setup() {
        initMocks(this);

        when(grid.next(row)).thenReturn(nextRow);
    }

    @Test
    public void shouldSetResistanceToZero() {
        underTest = getPathWithDefaultArgs();

        assertThat(underTest.getResistance(), is(noResistance));
    }

    @Test
    public void shouldSetPathToEmptyPath() {
        underTest = getPathWithDefaultArgs();

        assertThat(underTest.getPath(), is(empty()));
    }

    @Test
    public void shouldSetCanFlowToFalse() {
        underTest = getPathWithDefaultArgs();

        assertThat(underTest.canFlow(), is(false));
    }

    @Test
    public void shouldSetResistanceWhenBuildingPathWithNeighbor() {
        ResistancePath underTest = getPathWithNeighbor();

        assertThat(underTest.getResistance(), is(resistance));
    }


    @Test
    public void shouldSetPathWhenBuildingPathWithNeighbor() {
        ResistancePath underTest = getPathWithNeighbor();

        assertThat(underTest.getPath(), contains(nextRow));
    }


    @Test
    public void shouldSetCanFlowWhenBuildingPathWithNeighbor() {
        ResistancePath underTest = getPathWithNeighbor();

        assertThat(underTest.canFlow(), is(true));
    }

    @Test
    public void shouldSetResistanceWhenCreatingBlockedPath() {
        underTest = getBlockedPath();

        assertThat(underTest.getResistance(), is(resistance));
    }


    @Test
    public void shouldSetPathWhenCreatingABlockedPath() {
        underTest = getBlockedPath();

        assertThat(underTest.getPath(), contains(nextRow));
    }

    @Test
    public void shouldSetCanFlowToFalseWhenCreatingBlockedPath() {
        underTest = getBlockedPath();

        assertThat(underTest.canFlow(), is(false));
    }

    @Test
    public void shouldReturnTrueIfAddingNeighborResistanceExceedsMaximumToFlow() {
        underTest = getPathWithNeighbor();
        stubGridWith(neighborRow, neighborColumn, largerNeighborResistance);

        boolean blockingFlow = underTest.isNeighborBlockingFlow(grid, neighborRow, neighborColumn);
        assertThat(blockingFlow, is(true));
    }

    @Test
    public void shouldReturnFalseIfAddingNeighborResistanceNotExceedsMaximumToFlow() {
        underTest = getPathWithNeighbor();
        stubGridWith(neighborRow, neighborColumn, neighborResistance);

        boolean blockingFlow = underTest.isNeighborBlockingFlow(grid, neighborRow, neighborColumn);
        assertThat(blockingFlow, is(false));
    }

    private ResistancePath getPathWithDefaultArgs() {
        return new ResistancePath();
    }

    private ResistancePath getPathWithNeighbor() {
        stubGridWith(row, column, resistance);
        return getPathWithDefaultArgs().buildPathWithNeighbor(grid, row, column);
    }

    private ResistancePath getBlockedPath() {
        return getPathWithNeighbor().buildBlockedPath();
    }

    private void stubGridWith(int row, int column, int resistance) {
        when(grid.valueAt(row, column)).thenReturn(resistance);
    }
}
