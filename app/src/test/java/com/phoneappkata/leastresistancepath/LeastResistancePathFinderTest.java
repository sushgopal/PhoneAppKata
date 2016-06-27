package com.phoneappkata.leastresistancepath;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.emptySet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LeastResistancePathFinderTest {
    @InjectMocks
    @Spy
    private LeastResistancePathFinder underTest;

    @Mock
    private Grid grid;

    @Mock
    private ResistancePath currentPath;

    @Mock
    private ResistancePath neighbor1;

    @Mock
    private ResistancePath neighbor2;

    private ResistancePath result;

    private Set<Integer> noNeighbors = emptySet();

    private int root = -1;

    private int rowNumber = 1;

    private int columnNumber = 2;

    private int resistance = 10;

    private int neighBor1row = 0;

    private int neighBor2row = 1;

    private int neighBorcolumn = 3;

    private int neighbor1Resistance = 10;

    private int neighbor2Resistance = 40;

    private int largeResistance = 100;

    private List<Integer> neighbor1Path = newArrayList(1, 4);

    private List<Integer> neighbor2Path = newArrayList(2, 3);

    @Before
    public void setup() {
        initMocks(this);

        stubGrid();
        stubNewResistancePathInstance();
    }

    private void stubGrid() {
        stubGridRoot();
        stubGridValueToBe(resistance);
        stubGridNext();
    }

    private void stubGridNext() {
        when(grid.next(columnNumber)).thenReturn(columnNumber + 1);
    }

    private void stubNewResistancePathInstance() {
        doReturn(currentPath).when(underTest).getNewPath();
    }

    private void stubGridRoot() {
        when(grid.getRootRow()).thenReturn(root);
        when(grid.getRootColumn()).thenReturn(root);
    }

    @Test
    public void shoulReturnInstanceOfLeastResistancePath() {
        result = underTest.find();

        assertThat(result, isA(ResistancePath.class));
    }

    @Test
    public void shouldReturnLeastResistancePathAtGridRoot() {
        doReturn(result).when(underTest).findAt(root, root, currentPath);

        assertThat(underTest.find(), is(result));
    }

    @Test
    public void shouldGetNeighborsForGridRowAndColumn() {
        runTest();

        verify(grid).getNeighborRowsFor(rowNumber, columnNumber);
    }

    @Test
    public void shouldReturnCurrentPathIfNoNeighbors() {
        stubGridToReturnNoNeighbors();
        runTest();

        assertThat(result, is(currentPath));
    }

    @Test
    public void shouldBuildPathWithNeighbor() {
        stubGridToReturnNeighbors();
        runTest();

        verify(currentPath).buildPathWithNeighbor(grid, neighBor1row, neighBorcolumn);
    }

    @Test
    public void shouldFindResistancePathForEveryNeighbor() {
        stubGridToReturnNeighbors();
        runTest();

        verify(underTest).findAt(neighBor1row, neighBorcolumn, neighbor1);
        verify(underTest).findAt(neighBor2row, neighBorcolumn, neighbor2);
    }

    @Test
    public void shouldBuildBlockedPathIfNieghborBlocksFlow() {
        stubGridToReturnNeighbors();
        stubNeighborToBeBlocking();

        runTest();
        verify(currentPath).buildBlockedPath();
    }

    @Test
    public void shouldReturnLeastResistanceNeighborPath(){
        stubGridToReturnNeighbors();
        runTest();

        assertThat(result, is(neighbor1));
    }

    @Test
    public void shouldReturnLeastResistanceUnBlockedNeighborPathIfPresent(){
        stubGridToReturnNeighbors();
        stubNeighborToBeBlocking();
        runTest();

        assertThat(result, is(neighbor2));
    }

    private void runTest() {
        result = underTest.findAt(rowNumber, columnNumber, currentPath);
    }

    private void stubNeighborToBeBlocking() {
        when(neighbor1.getResistance()).thenReturn(largeResistance);
        when(currentPath.isNeighborBlockingFlow(grid, neighBor1row, neighBorcolumn)).thenReturn(true);
        when(currentPath.buildBlockedPath()).thenReturn(neighbor1);
    }

    private void stubGridToReturnNoNeighbors() {
        when(grid.getNeighborRowsFor(rowNumber, columnNumber)).thenReturn(noNeighbors);
    }

    private void stubGridToReturnNeighbors() {
        stubNeighbors();
        stubLeastResistancePathForNeighbors();
        when(grid.getNeighborRowsFor(rowNumber, columnNumber)).thenReturn(newHashSet(0, 1));
    }

    private void stubLeastResistancePathForNeighbors() {
        doReturn(neighbor1).when(underTest).findAt(neighBor1row, neighBorcolumn, neighbor1);
        doReturn(neighbor2).when(underTest).findAt(neighBor2row, neighBorcolumn, neighbor2);
    }

    private void stubNeighbors() {
        when(currentPath.buildPathWithNeighbor(grid, neighBor1row, neighBorcolumn)).thenReturn(neighbor1);
        when(currentPath.buildPathWithNeighbor(grid, neighBor2row, neighBorcolumn)).thenReturn(neighbor2);

        stubNeighborWith(neighbor1, neighbor1Resistance, neighbor1Path);
        stubNeighborWith(neighbor2, neighbor2Resistance, neighbor2Path);
    }

    private void stubNeighborWith(ResistancePath neighbor, int resistance, List<Integer> path) {
        when(neighbor.getResistance()).thenReturn(resistance);
        when(neighbor.getPath()).thenReturn(path);
        when(neighbor.canFlow()).thenReturn(true);
    }

    private void stubGridValueToBe(int resistance) {
        when(grid.valueAt(rowNumber, columnNumber)).thenReturn(resistance);
    }
}
