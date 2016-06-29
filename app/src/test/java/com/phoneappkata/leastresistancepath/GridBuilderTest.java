package com.phoneappkata.leastresistancepath;

import com.phoneappkata.activity.EditTextAdapter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GridBuilderTest {
    GridBuilder underTest = new GridBuilder();

    @Mock
    private EditTextAdapter adapter;

    private int rows = 2;

    private int columns = 2;

    private Grid grid;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        stubAdapter();
        grid = underTest.buildFrom(rows, columns, adapter);
    }

    private void stubAdapter() {
        when(adapter.getItem(0)).thenReturn(0);
        when(adapter.getItem(1)).thenReturn(1);
        when(adapter.getItem(2)).thenReturn(2);
        when(adapter.getItem(3)).thenReturn(3);
    }

    @Test
    public void shouldHaveExpectedNumberOfRows() {
        assertThat(grid.numberOfRows(), is(rows));
    }

    @Test
    public void shouldHaveExpectedNumberOfColumns() {
        assertThat(grid.numberOfColumns(), is(columns));
    }

    @Test
    public void shouldSetGridValuesFromAdapter() {
        assertThat(grid.valueAt(0, 0), is(0));
        assertThat(grid.valueAt(1, 0), is(2));
    }
}
