package com.phoneappkata.leastresistancepath;

import com.google.common.collect.Lists;

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

    private ResistancePath getPathWithDefaultArgs() {
        return new ResistancePath();
    }

    private ResistancePath getPathWithNeighbor() {
        stubGridWith(row, column, resistance);
        return getPathWithDefaultArgs().buildPathWithNeighbor(grid, row, column);
    }

    private void stubGridWith(int row, int column, int resistance) {
        when(grid.valueAt(row, column)).thenReturn(resistance);
    }
}
