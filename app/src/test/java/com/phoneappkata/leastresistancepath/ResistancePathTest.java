package com.phoneappkata.leastresistancepath;

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;

public class ResistancePathTest {

    private ResistancePath resistancePath;

    private int rowNumber = 0;

    private int columnNumber = 0;

    private int resistance = 4;

    private int path = 1;

    private int neighborResistance = 10;

    private int largerNeighborResistance = 48;

    private int neighborPath = 4;

    private int noResistance = 0;

    private boolean cannotFlow = false;

    @Mock
    private Grid grid;

    @Mock
    private ResistancePath leastResistanceNeighbor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        stubGridWith(resistance);
    }

    @Test
    public void shouldReturnTrueIfAddingResistanceAtGridExceedsMaximumToFlow() {
        resistancePath = new ResistancePath(resistance, null, true);
        stubGridWith(largerNeighborResistance);
        assertThat(resistancePath.isNeighborBlockingFlow(grid, rowNumber, columnNumber), is(true));
    }

    @Test
    public void shouldReturnFalseIfAddingResistanceAtGridNotExceedsMaximumToFlow() {
        resistancePath = new ResistancePath(resistance, null, true);
        stubGridWith(neighborResistance);
        assertThat(resistancePath.isNeighborBlockingFlow(grid, rowNumber, columnNumber), is(false));
    }

    @Test
    public void shouldSetResistanceToZero() {
        resistancePath = new ResistancePath();

        assertThat(resistancePath.getResistance(), is(noResistance));
    }

    @Test
    public void shouldSetPathToEmptyPath() {
        resistancePath = new ResistancePath();

        assertThat(resistancePath.getPath(), is(empty()));
    }

    @Test
    public void shouldSetCanFlowToFalse() {
        resistancePath = new ResistancePath();

        assertThat(resistancePath.canFlow(), is(cannotFlow));
    }

    @Test
    public void shouldSetResistance() {
        resistancePath = new ResistancePath(resistance, Lists.<Integer>newArrayList(path), true);
        stubGridWith(neighborResistance);
        ResistancePath result = resistancePath.buildPathWithNeighbor(grid, rowNumber, columnNumber);

        assertThat(result.getResistance(), is(resistance+neighborResistance));
    }


    @Test
    public void shouldSetPath() {
        resistancePath = new ResistancePath(resistance, Lists.<Integer>newArrayList(path), true);
        stubGridWith(neighborResistance);
        ResistancePath result = resistancePath.buildPathWithNeighbor(grid, rowNumber, columnNumber);

        assertThat(result.getPath(), contains(path, rowNumber+1));
    }


    @Test
    public void shouldSetCanFlow() {
        resistancePath = new ResistancePath(resistance, Lists.<Integer>newArrayList(path), true);
        stubGridWith(neighborResistance);
        ResistancePath result = resistancePath.buildPathWithNeighbor(grid, rowNumber, columnNumber);

        assertThat(result.canFlow(), is(true));
    }

    @Test
    public void shouldSetResistanceWhenCreatingBlockedPath() {
        resistancePath = new ResistancePath(resistance, Lists.<Integer>newArrayList(path), true);

        ResistancePath result = resistancePath.buildBlockedPath();

        assertThat(result.getResistance(), is(resistance));
    }


    @Test
    public void shouldSetPathWhenCreatingABlockedPath() {
        resistancePath = new ResistancePath(resistance, Lists.<Integer>newArrayList(path), true);

        ResistancePath result = resistancePath.buildBlockedPath();

        assertThat(result.getPath(), contains(path));
    }

    @Test
    public void shouldSetCanFlowToFalseWhenCreatingBlockedPath() {
        resistancePath = new ResistancePath(resistance, null, true);

        ResistancePath result = resistancePath.buildBlockedPath();

        assertThat(result.canFlow(), is(false));
    }

    private void createLeastResistancePathWithNeighbor() {
//        resistancePath = new ResistancePath(grid, rowNumber, columnNumber, leastResistanceNeighbor);
    }

    private void createLeastResistancePath() {
//        resistancePath = new ResistancePath(grid, rowNumber, columnNumber);
    }

    private void stubGridWith(int resistance) {
        when(grid.valueAt(rowNumber, columnNumber)).thenReturn(resistance);
    }
}
