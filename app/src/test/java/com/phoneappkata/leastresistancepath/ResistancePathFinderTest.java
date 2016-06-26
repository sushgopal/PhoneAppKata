package com.phoneappkata.leastresistancepath;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.emptySet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ResistancePathFinderTest {
    @InjectMocks
    @Spy
    private ResistancePathFinder underTest;

    @Mock
    private Grid grid;

    @Mock
    private LeastResistancePath path;

    @Mock
    private LeastResistancePath pathFor0And3;

    @Mock
    private LeastResistancePath pathFor1And3;

    private LeastResistancePath result;

    private static Set<Integer> NO_NEIGHBORS = emptySet();

    private static int ROW_NUMBER = 1;

    private static int COLUMN_NUMBER = 2;

    private static int RESISTANCE_1 = 10;

    private static int RESISTANCE_2 = 20;

    private static String PATH_1 = "1 4";

    private static String PATH_2 = "2 3";

    @Before
    public void setup() {
        initMocks(this);

        doReturn(pathFor0And3).when(underTest).findAt(0, 3);
        doReturn(pathFor1And3).when(underTest).findAt(1, 3);

        stubGridValuesAtRowAndColumn(RESISTANCE_1);
        stubNeighborPaths();
    }

    @Test
    public void shouldSetPathToOneAddedToRowIndexWhenThereAreNoNeighborRows() {
        stubGridToReturnNoNieghbors();
        runTest();

        assertThat(result.getPath(), is("2"));
    }

    @Test
    public void shouldSetResistanceToValueAtRowAndColumnWhenThereAreNoNeighborRows() {
        stubGridToReturnNoNieghbors();
        runTest();

        assertThat(result.getResistance(), is(10));
    }

    @Test
    public void shouldAddResultToVisitedNodesThereAreNoNeighborRows() {
        stubGridToReturnNoNieghbors();
        runTest();

        assertThat(underTest.visitedNodes.get("1:2"), is(result));
    }

    @Test
    public void shouldMakeRecursiveCallForEachNeighborRow() {
        stubGridToReturnNeighbors();
        runTest();

        verify(underTest).findAt(0, 3);
        verify(underTest).findAt(1, 3);
    }

    @Test
    public void shouldNotMakeRecursiveCallForAlreadyVisitedRowAndColumn() {
        underTest.visitedNodes.put("1:3", path);
        stubGridToReturnNeighbors();
        runTest();

        verify(underTest, never()).findAt(1, 3);
    }

    @Test
    public void shouldSetResistanceAsCurrentResistanceAddedToLeastResistanceFromNeighbors(){
        stubGridToReturnNeighbors();

        runTest();
        assertThat(result.getResistance(), is(20));
    }

    @Test
    public void shouldSetPathAsCurrentRowNumberAppendedToPathOfLeastResistanceNeighbor(){
        stubGridToReturnNeighbors();
        String expected = "2 1 4";

        runTest();
        assertThat(result.getPath(), is(expected));
    }

    @Test
    public void shouldSetCanFlowToTrueIfLeastResistanceIsLessThanFifty() {
        stubGridToReturnNeighbors();

        runTest();
        assertThat(result.canFlow(), is(true));
    }

    @Test
    public void shouldSetCanFlowToTrueIfLeastResistanceIsEqualToFifty() {
        stubGridToReturnNeighbors();
        stubNeighborPathsToReturnResistance(20, 30);
        stubGridValuesAtRowAndColumn(30);

        runTest();
        assertThat(result.canFlow(), is(true));
    }

    @Test
    public void shouldSetCanFlowToFalseIfLeastResistanceIsMoreThanFifty() {
        stubGridToReturnNeighbors();
        stubNeighborPathsToReturnResistance(100, 200);

        runTest();
        assertThat(result.canFlow(), is(false));
    }

    @Test
    public void shouldNotAddLeastResistanceFromNeighborWhenTheSumWillExceedFifty(){
        stubGridToReturnNeighbors();
        stubNeighborPathsToReturnResistance(40, 45);
        stubGridValuesAtRowAndColumn(15);

        runTest();
        assertThat(result.getResistance(), is(15));
    }

    @Test
    public void shouldNotAppendLeastResistantPathWhenTheResistanceWillExceedFifty(){
        stubGridToReturnNeighbors();
        stubNeighborPathsToReturnResistance(45, 40);
        stubGridValuesAtRowAndColumn(15);

        runTest();
        assertThat(result.getPath(), is("2"));
    }

    private void runTest() {
        result = underTest.findAt(ROW_NUMBER, COLUMN_NUMBER);
    }

    private void stubGridToReturnNoNieghbors() {
        when(grid.getNeighborRowsFor(ROW_NUMBER, COLUMN_NUMBER)).thenReturn(NO_NEIGHBORS);
    }

    private void stubGridToReturnNeighbors() {
        when(grid.getNeighborRowsFor(ROW_NUMBER, COLUMN_NUMBER)).thenReturn(newHashSet(0, 1));
    }

    private void stubNeighborPaths() {
        stubNeighborPathsToReturnResistance(RESISTANCE_1, RESISTANCE_2);
        when(pathFor0And3.getPath()).thenReturn(PATH_1);
        when(pathFor1And3.getPath()).thenReturn(PATH_2);
    }

    private void stubNeighborPathsToReturnResistance(int resistance1, int resistance2) {
        when(pathFor0And3.getResistance()).thenReturn(resistance1);
        when(pathFor1And3.getResistance()).thenReturn(resistance2);
    }

    private void stubGridValuesAtRowAndColumn(int resistance) {
        when(grid.valueAt(ROW_NUMBER, COLUMN_NUMBER)).thenReturn(resistance);
    }
}
