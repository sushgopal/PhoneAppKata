//package com.phoneappkata.leastresistancepath;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.Spy;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//import static org.hamcrest.Matchers.isA;
//import static org.mockito.Matchers.any;
//import static org.mockito.Matchers.eq;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//public class LeastResistancePathFinderTest {
//
//    @Spy
//    @InjectMocks
//    private LeastResistancePathFinder underTest;
//
//    private int[][] gridArray = new int[3][3];
//
//    @Mock
//    private Grid grid;
//
//    @Mock
//    private  ResistancePathFinder finder;
//
//    @Mock
//    private ResistancePath path1;
//
//    @Mock
//    private ResistancePath path2;
//
//    @Mock
//    private ResistancePath path3;
//
//    private ResistancePath result;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//
//        doReturn(grid).when(underTest).getGrid(gridArray);
//        when(grid.numberOfRows()).thenReturn(3);
//        doReturn(finder).when(underTest).getFinder(grid);
//        doReturn(path1).when(finder).findAt(0, 0);
//        doReturn(path2).when(finder).findAt(1, 0);
//        doReturn(path3).when(finder).findAt(2, 0);
//        setupPaths();
//    }
//
//    private void setupPaths() {
//        when(path1.getResistance()).thenReturn(8);
//        when(path2.getResistance()).thenReturn(2);
//        when(path3.getResistance()).thenReturn(5);
//    }
//
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
//}
