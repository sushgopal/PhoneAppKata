package com.phoneappkata.leastresistancepath;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.emptySet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
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
    private ResistancePath neighbor1;

    @Mock
    private ResistancePath neighbor2;

    private ResistancePath result;

    private Set<Integer> noNeighbors = emptySet();

    private int rowNumber = 1;

    private int columnNumber = 2;

    private int resistance = 10;

    private List<Integer> path = newArrayList(2);

    private int neighBor1row = 0;

    private int neighBor2row = 1;

    private int neighBorcolumn = 3;

    private int neighbor1Resistance = 10;

    private int neighbor2Resistance = 40;

    private List<Integer> neighbor1Path = newArrayList(1, 4);

    private List<Integer> neighbor2Path = newArrayList(2, 3);

    @Before
    public void setup() {
        initMocks(this);

        stubGridValueToBe(resistance);
    }

    //    @Test
//    public void shoulReturnInstanceOfLeastResistancePath() {
//        result = underTest.find(gridArray);
//
//        assertThat(result, isA(ResistancePath.class));
//    }
//
//    @Test
//    public void shouldFindPathAtGridOrigin() {
//        result = underTest.find(gridArray);
//
//        verify(finder).findAt(0, 0);
//    }
//
//    @Test
//    public void shouldFindPathForFirstCellOfEveryRowInGrid() {
//        result = underTest.find(gridArray);
//
//        verify(finder).findAt(0, 0);
//        verify(finder).findAt(1, 0);
//        verify(finder).findAt(2, 0);
//    }
//
//    @Test
//    public void shouldReturnPathOfLeastResistance() {
//        result = underTest.find(gridArray);
//
//        assertThat(result, is(path2));
//    }

//    @Test
//    public void shouldSetPath() {
//        stubGridToReturnNoNeighbors();
//        runTest();
//
//        assertThat(result.getPath(), is(path));
//    }
//
//    @Test
//    public void shouldSetResistance() {
//        stubGridToReturnNoNeighbors();
//        runTest();
//
//        assertThat(result.getResistance(), is(resistance));
//    }
//
//    @Test
//    public void shouldMakeRecursiveCallForEachNeighborRow() {
//        stubGridToReturnNeighbors();
//        runTest();
//
//        verify(underTest).findAt(neighBor1row, neighBorcolumn);
//        verify(underTest).findAt(neighBor2row, neighBorcolumn);
//    }
//
//    @Test
//    public void shouldSetPathWithLeastResistanceNeighbor(){
//        stubGridToReturnNeighbors();
//        runTest();
//
//        assertThat(result.getPath(), contains(path.get(0), neighbor1Path.get(0), neighbor1Path.get(1)));
//    }
//
//    @Test
//    public void shouldSetResistanceWithLeastResistanceNeighbor(){
//        stubGridToReturnNeighbors();
//
//        runTest();
//        assertThat(result.getResistance(), is(resistance+neighbor1Resistance));
//    }

    private void runTest() {
//        result = underTest.findAt(rowNumber, columnNumber);
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
//        doReturn(neighbor1).when(underTest).findAt(neighBor1row, neighBorcolumn);
//        doReturn(neighbor2).when(underTest).findAt(neighBor2row, neighBorcolumn);
    }

    private void stubNeighbors() {
        when(neighbor1.getResistance()).thenReturn(neighbor1Resistance);
        when(neighbor1.getPath()).thenReturn(neighbor1Path);
        when(neighbor2.getResistance()).thenReturn(neighbor2Resistance);
        when(neighbor2.getPath()).thenReturn(neighbor2Path);
    }

    private void stubGridValueToBe(int resistance) {
        when(grid.valueAt(rowNumber, columnNumber)).thenReturn(resistance);
    }
}
