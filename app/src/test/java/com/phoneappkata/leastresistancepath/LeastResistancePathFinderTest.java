package com.phoneappkata.leastresistancepath;

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.asList;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.emptySet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
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
    private ResistancePath leastResistancePath;
    @Mock
    private ResistancePath leastResistancePathForNeighbor1;
    @Mock
    private ResistancePath leastResistancePathForNeighbor2;
    @Mock
    private ResistancePath blockedPath;
    @Mock
    private VisitedNodes visitedCells;

    private ResistancePath result;

    private Set<Integer> noNeighbors = emptySet();

    private int root = -1;

    private int rowNumber = 1;

    private int columnNumber = 2;

    private int resistance = 10;

    private int neighborColumn = 3;

    private int neighbor1Row = 0;

    private int neighbor1Resistance = 10;

    private List<Integer> neighbor1Path = newArrayList(1, 2);

    private int neighBor2row = 1;

    private int neighbor2Resistance = 40;

    private List<Integer> neighbor2Path = newArrayList(2, 3);

    private final int largerResistance = 50;

    @Before
    public void setup() {
        initMocks(this);
        stubGrid();
    }

    private void stubGrid() {
        stubGridRoot();
        stubGridValueToBe(resistance);
        stubGridNext();
    }

    private void stubGridNext() {
        when(grid.next(columnNumber)).thenReturn(columnNumber + 1);
    }

    private void stubGridRoot() {
        when(grid.getRootRow()).thenReturn(root);
        when(grid.getRootColumn()).thenReturn(root);
    }

    @Test
    public void shouldReturnInstanceOfResistancePath() {
        assertThat(underTest.find(), isA(ResistancePath.class));
    }

    @Test
    public void shouldReturnLeastResistancePath() {
        stubFindAtRoot();

        assertThat(underTest.find(), is(leastResistancePath));
    }

    @Test
    public void shouldGetNeighborsForGridRowAndColumn() {
        runFindAtTest();

        verify(grid).getNeighborRowsFor(rowNumber, columnNumber);
    }

    @Test
    public void shouldReturnNewPathIfNoNeighbors() {
        stubGridToReturnNoNeighbors();
        doReturn(leastResistancePath).when(underTest).getResistancePath(rowNumber, columnNumber);
        runFindAtTest();

        assertThat(result, is(leastResistancePath));
    }

    @Test
    public void shouldAddResultToVisitedNodes() {
        underTest.setVisitedCells(visitedCells);
        stubGridToReturnNoNeighbors();
        doReturn(leastResistancePath).when(underTest).getResistancePath(rowNumber, columnNumber);
        runFindAtTest();

        verify(visitedCells).add(rowNumber, columnNumber, leastResistancePath);
    }

    @Test
    public void shouldMakeRecursiveCallForEachNeighbor() {
        stubGridToReturnNeighbors();
        runFindAtTest();

        verify(underTest).findAt(neighbor1Row, neighborColumn);
        verify(underTest).findAt(neighBor2row, neighborColumn);
    }

    @Test
    public void shouldNotMakeRecursiveCallForAlreadyVisitedNeighbor() {
        underTest.setVisitedCells(visitedCells);

        stubGridToReturnNeighbors();
        stubVisitedCellsForNeighbor1();
        runFindAtTest();

        verify(underTest, never()).findAt(neighbor1Row, neighborColumn);
    }

    @Test
    public void shouldBuildPathWithLeastResistanceNeighbor() {
        stubGridToReturnNeighbors();
        runFindAtTest();

        verify(leastResistancePathForNeighbor1).buildPathWith(grid, rowNumber, columnNumber);
    }

    @Test
    public void shouldSetCanFlowToFalse() {
        setupBlockedPath();

        assertThat(underTest.find().canFlow(), is(false));
    }

    @Test
    public void shouldSetResistanceUptoBlockedCell() {
        setupBlockedPath();

        assertThat(underTest.find().getResistance(), is(largerResistance));
    }

    @Test
    public void shouldAddPathUptoBlockedCell() {
        setupBlockedPath();

        assertThat(underTest.find().getPath(), contains(1));
    }

    private void setupBlockedPath() {
        stubFindAtRoot();
        when(leastResistancePath.isABlockedPath()).thenReturn(true);
        when(leastResistancePath.getPath()).thenReturn(Lists.<Integer>newArrayList(1, 2));
        when(grid.previous(1)).thenReturn(0);
        when(grid.previous(2)).thenReturn(1);
        when(grid.valueAt(0, 0)).thenReturn(largerResistance);
        when(grid.valueAt(1, 1)).thenReturn(largerResistance);
    }

    private void stubVisitedCellsForNeighbor1() {
        when(visitedCells.visited(neighbor1Row, neighborColumn)).thenReturn(true);
    }

    private void stubFindAtRoot() {
        doReturn(leastResistancePath).when(underTest).findAt(root, root);
    }

    private void runFindAtTest() {
            result = underTest.findAt(rowNumber, columnNumber);
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
        doReturn(leastResistancePathForNeighbor1).when(underTest).findAt(neighbor1Row, neighborColumn);
        doReturn(leastResistancePathForNeighbor2).when(underTest).findAt(neighBor2row, neighborColumn);
    }

    private void stubNeighbors() {
        stubNeighborWith(leastResistancePathForNeighbor1, neighbor1Resistance, neighbor1Path);
        stubNeighborWith(leastResistancePathForNeighbor2, neighbor2Resistance, neighbor2Path);
    }

    private void stubNeighborsWithLargerResistance() {
        stubNeighborWith(leastResistancePathForNeighbor1, largerResistance, neighbor1Path);
        stubNeighborWith(leastResistancePathForNeighbor2, largerResistance, neighbor2Path);
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

