package com.phoneappkata.leastresistancepath;

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;

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
    private LeastResistancePathFinder underTest;

    private int[][] grid = new int[3][3];

    @Mock
    private LeastResistancePath path1;

    @Mock
    private LeastResistancePath path2;

    @Mock
    private LeastResistancePath path3;

    private LeastResistancePath result;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        setupPaths();
        doReturn(path1).when(underTest).findAt(0, 0, grid);
        doReturn(path2).when(underTest).findAt(1, 0, grid);
        doReturn(path3).when(underTest).findAt(2, 0, grid);
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

        verify(underTest).findAt(0, 0, grid);
    }

    @Test
    public void shouldFindPathForEveryRowInGrid() {
        result = underTest.find(grid);

        verify(underTest, times(3)).findAt(anyInt(), anyInt(), eq(grid));
    }

    @Test
    public void shouldFindPathForFirstCellOfEveryRowInGrid() {
        result = underTest.find(grid);

        verify(underTest).findAt(0, 0, grid);
        verify(underTest).findAt(1, 0, grid);
        verify(underTest).findAt(2, 0, grid);
    }

    @Test
    public void shouldReturnPathOfLeastResistance() {
        result = underTest.find(grid);

        assertThat(result, is(path2));
    }

    @Test
    public void shouldAddRowIndexToPath() {
        grid[2][2] = 5;
        result = underTest.findAt(2, 2, grid);

        assertThat(result.getPath(), is("2"));
    }

    @Test
    public void shouldSetResistanceToCellValue() {
        grid[2][2] = 5;
        result = underTest.findAt(2, 2, grid);

        assertThat(result.getResistance(), is(5));
    }

    @Test
    public void shouldCallFindNextPossibleCells() {
        underTest.findAt(2, 2, grid);

        verify(underTest).findPossibleNeighborCellsToFlow(2, 2);
    }

    @Test
    public void shouldFindLeastResistancePathForEveryPossibleNextCell() {
        Cell cell1 = new Cell(0, 2);
        Cell cell2 = new Cell(1, 2);
        List<Cell> nextPossibleCells = Lists.newArrayList(cell1, cell2);
        doReturn(nextPossibleCells).when(underTest).findPossibleNeighborCellsToFlow(1, 1);

        underTest.findAt(1, 1, grid);
        verify(underTest).findAt(0, 2, grid);
        verify(underTest).findAt(1, 2, grid);
    }

    @Test
    public void shouldReturnMiniumResistancePathOfAllNeighborPaths() {
        Cell cell1 = new Cell(0, 2);
        Cell cell2 = new Cell(1, 2);
        grid[1][1]=98;
        List<Cell> nextPossibleCells = Lists.newArrayList(cell1, cell2);
        doReturn(nextPossibleCells).when(underTest).findPossibleNeighborCellsToFlow(1, 1);
        doReturn(path2).when(underTest).findAt(0, 2, grid);
        doReturn(path1).when(underTest).findAt(1, 2, grid);

        result = underTest.findAt(1, 1, grid);

        assertThat(result, is(path2));
    }
}
