package com.phoneappkata.leastresistancepath;

import com.google.common.collect.Sets;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LeastResistancePathFinderTest {
    @Spy
    @InjectMocks
    private LeastResistancePathFinder underTest;

    private int[][] grid = new int[3][3];

    @Mock
    private LeastResistancePath path1;

    @Mock
    private LeastResistancePath path2;

    @Mock
    private LeastResistancePath path3;

    private LeastResistancePath result;

    @Mock
    private NeighborCells neighborCells;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        setupPaths();
        doReturn(path1).when(underTest).findAt(0, 0, grid, result);
        doReturn(path2).when(underTest).findAt(1, 0, grid, result);
        doReturn(path3).when(underTest).findAt(2, 0, grid, result);
    }

    private void setupPaths() {
        when(path1.getResistance()).thenReturn(8);
        when(path2.getResistance()).thenReturn(2);
        when(path3.getResistance()).thenReturn(5);
    }

    @Test
    public void shoulReturnInstanceOfLeastResistancePath() {
        result = underTest.find(grid);

        assertThat(result, isA(LeastResistancePath.class));
    }

    @Test
    public void shouldFindPathAtGridOrigin() {
        result = underTest.find(grid);

        verify(underTest).findAt(0, 0, grid, null);
    }

    @Test
    public void shouldFindPathForEveryRowInGrid() {
        result = underTest.find(grid);

        verify(underTest, times(3)).findAt(anyInt(), anyInt(), eq(grid), any(LeastResistancePath.class));
    }

    @Test
    public void shouldFindPathForFirstCellOfEveryRowInGrid() {
        result = underTest.find(grid);

        verify(underTest).findAt(eq(0), eq(0), eq(grid), any(LeastResistancePath.class));
        verify(underTest).findAt(eq(1), eq(0), eq(grid), any(LeastResistancePath.class));
        verify(underTest).findAt(eq(2), eq(0), eq(grid), any(LeastResistancePath.class));
    }

    @Test
    public void shouldReturnPathOfLeastResistance() {
        result = underTest.find(grid);

        assertThat(result, is(path2));
    }

    @Test
    public void shouldAddRowIndexToPathWhenNoNieghborCells() {
        grid[2][2] = 5;
        result = underTest.findAt(2, 2, grid, result);

        assertThat(result.getPath(), is("3"));
    }

    @Test
    public void shouldSetResistanceToCellValueWhenNoNeighborCells() {
        grid[2][2] = 5;
        result = underTest.findAt(2, 2, grid, result);

        assertThat(result.getResistance(), is(5));
    }

    @Test
    public void shouldCallFindNextPossibleCells() {
        underTest.findAt(2, 2, grid, result);

        verify(neighborCells).findFor(new Cell(2, 2), grid);
    }

    @Test
    public void shouldFindLeastResistancePathForEveryNeighborCell() {
        Cell cell1 = new Cell(0, 2);
        Cell cell2 = new Cell(1, 2);
        Set<Cell> nextPossibleCells = Sets.newHashSet(cell1, cell2);
        doReturn(nextPossibleCells).when(neighborCells).findFor(new Cell(1, 1), grid);
        doReturn(path2).when(underTest).findAt(eq(0), eq(2), eq(grid), any(LeastResistancePath.class));
        doReturn(path1).when(underTest).findAt(eq(1), eq(2), eq(grid), any(LeastResistancePath.class));

        underTest.findAt(1, 1, grid, null);
        verify(underTest).findAt(eq(0), eq(2), eq(grid), any(LeastResistancePath.class));
        verify(underTest).findAt(eq(1), eq(2), eq(grid), any(LeastResistancePath.class));
    }

    @Test
    public void shouldReturnMiniumResistancePathOfAllNeighborPaths() {
        Cell cell1 = new Cell(0, 2);
        Cell cell2 = new Cell(1, 2);
        grid[1][1]=0;
        Set<Cell> nextPossibleCells = Sets.newHashSet(cell1, cell2);

        doReturn(nextPossibleCells).when(neighborCells).findFor(new Cell(1, 1), grid);
        doReturn(path2).when(underTest).findAt(0, 2, grid, path1);
        doReturn(path1).when(underTest).findAt(1, 2, grid, null);

        result = underTest.findAt(1, 1, grid, result);

        assertThat(result.getResistance(), is(path2.getResistance()));
    }
}
