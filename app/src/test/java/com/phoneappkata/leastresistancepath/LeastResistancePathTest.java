package com.phoneappkata.leastresistancepath;

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

public class LeastResistancePathTest {

    private LeastResistancePath leastResistancePath;

    private int rowNumber = 0;

    private int columnNumber = 0;

    private int resistance = 4;

    private int path = 1;

    private int neighborResistance = 10;

    private int largerNeighborResistance = 48;

    private List<Integer> neighborPath = Lists.newArrayList(4 , 5);

    private int maximumResistanceToFlow = 50;

    @Mock
    private Grid grid;

    @Mock
    private LeastResistancePath leastResistanceNeighbor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        stubGridWith(resistance);
    }

    @Test
    public void shouldSetResistanceToGridValue() {
        createLeastResistancePath();

        assertThat(leastResistancePath.getResistance(), is(resistance));
    }

    @Test
    public void shouldAddRowToPath() {
        createLeastResistancePath();

        assertThat(leastResistancePath.getPath(), contains(path));
    }

    @Test
    public void shouldSetCanFlowToTrueIfResistanceIsLessThanMaxResistanceToFlow() {
        createLeastResistancePath();

        assertThat(leastResistancePath.canFlow(), is(true));
    }

    @Test
    public void shouldSetCanFlowToTrueIfResistanceEqualsMaxResistanceToFlow() {
        stubGridWith(maximumResistanceToFlow);
        createLeastResistancePath();

        assertThat(leastResistancePath.canFlow(), is(true));
    }

    @Test
    public void shouldSetCanFlowToFalseIfResistanceExceedsMaxResistanceToFlow() {
        stubGridWith(maximumResistanceToFlow+1);
        createLeastResistancePath();

        assertThat(leastResistancePath.canFlow(), is(false));
    }

    @Test
    public void shouldSetResistanceAsGridValueAddedToResistanceOfNeighbor() {
        stubLeastResistanceNeighborWith(neighborResistance, neighborPath);
        createLeastResistancePathWithNeighbor();

        assertThat(leastResistancePath.getResistance(), is(resistance+neighborResistance));
    }

    @Test
    public void shouldSetResistanceAsGridValueWhenTotalResistanceWithNeighborExceedsMaximum() {
        stubLeastResistanceNeighborWith(largerNeighborResistance, neighborPath);
        createLeastResistancePathWithNeighbor();

        assertThat(leastResistancePath.getResistance(), is(resistance));
    }

    @Test
    public void shouldAddRowToNeighborPath() {
        stubLeastResistanceNeighborWith(neighborResistance, neighborPath);
        createLeastResistancePathWithNeighbor();

        assertThat(leastResistancePath.getPath(), contains(path, neighborPath.get(0), neighborPath.get(1)));
    }

    @Test
    public void shouldAddRowToPathWhenTotalResistanceWithNeighborExceedsMaximum() {
        stubLeastResistanceNeighborWith(largerNeighborResistance, neighborPath);
        createLeastResistancePathWithNeighbor();

        assertThat(leastResistancePath.getPath(), contains(path));
    }

    @Test
    public void shouldSetCanFlowToTrueWhenTotalResistanceDoesNotExceedMaximum() {
        stubLeastResistanceNeighborWith(neighborResistance, neighborPath);
        createLeastResistancePathWithNeighbor();

        assertThat(leastResistancePath.canFlow(), is(true));
    }

    @Test
    public void shouldSetCanFlowToFalseWhenTotalResistanceExceedsMaximum() {
        stubLeastResistanceNeighborWith(largerNeighborResistance, neighborPath);
        createLeastResistancePathWithNeighbor();

        assertThat(leastResistancePath.canFlow(), is(false));
    }

    private void stubLeastResistanceNeighborWith(int resistance, List<Integer> path) {
        when(leastResistanceNeighbor.getResistance()).thenReturn(resistance);
        when(leastResistanceNeighbor.getPath()).thenReturn(path);
    }

    private void createLeastResistancePathWithNeighbor() {
        leastResistancePath = new LeastResistancePath(grid, rowNumber, columnNumber, leastResistanceNeighbor);
    }

    private void createLeastResistancePath() {
        leastResistancePath = new LeastResistancePath(grid, rowNumber, columnNumber);
    }

    private void stubGridWith(int resistance) {
        when(grid.valueAt(rowNumber, columnNumber)).thenReturn(resistance);
    }
}
