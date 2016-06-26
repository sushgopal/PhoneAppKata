package com.phoneappkata.leastresistancepath;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

public class LeastResistancePathTest {

    private LeastResistancePath leastResistancePath;

    private int rowNumber = 0;

    private int columnNumber = 0;

    private int neighborResistance = 10;

    private int resistance = 4;

    private String path = "1";

    private String neighborPath = "4 5";

    private int largerNeighborResistance = 48;

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
    public void shouldSetPathToOneAddedToRow() {
        createLeastResistancePath();

        assertThat(leastResistancePath.getPath(), is(path));
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
        stubLeastResistanceNeighborWith(neighborResistance);
        createLeastResistancePathWithNeighbor();

        assertThat(leastResistancePath.getResistance(), is(resistance+neighborResistance));
    }

    @Test
    public void shouldSetResistanceAsGridValueWhenTotalResistanceWithNeighborExceedsMaximum() {
        stubLeastResistanceNeighborWith(largerNeighborResistance);
        createLeastResistancePathWithNeighbor();

        assertThat(leastResistancePath.getResistance(), is(resistance));
    }

    @Test
    public void shouldSetPathAsRowAppendedToNeighborPath() {
        stubLeastResistanceNeighborWith(neighborResistance, neighborPath);
        createLeastResistancePathWithNeighbor();
        String expectedPath = path + " " + neighborPath;

        assertThat(leastResistancePath.getPath(), is(expectedPath));
    }

    @Test
    public void shouldSetPathAsOneAddedToRowWhenTotalResistanceWithNeighborExceedsMaximum() {
        stubLeastResistanceNeighborWith(largerNeighborResistance, neighborPath);
        createLeastResistancePathWithNeighbor();

        assertThat(leastResistancePath.getPath(), is(path));
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

    private void stubLeastResistanceNeighborWith(int resistance) {
        stubLeastResistanceNeighborWith(resistance, null);
    }

    private void stubLeastResistanceNeighborWith(int resistance, String path) {
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
