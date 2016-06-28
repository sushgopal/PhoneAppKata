package com.phoneappkata;

import android.content.Intent;
import android.widget.GridView;

import com.phoneappkata.leastresistancepath.Grid;
import com.phoneappkata.leastresistancepath.ResistancePath;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;
import static com.phoneappkata.R.id.input_grid;
import static com.phoneappkata.R.string.grid_column_count;
import static com.phoneappkata.R.string.grid_row_count;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GridInputActivityUnitTest {

    @Spy
    GridInputActivity underTest = new GridInputActivity();
    
    private final int resistance = 20;

    private final ArrayList<Integer> resistancePath = newArrayList(1, 1);

    @Mock
    private Intent intent;
    @Mock
    private android.os.Bundle bundle;
    @Mock
    private EditTextAdapter adapter;

    private int defaultValue = 0;

    private int rows = 5;

    private int columns = 5;

    @Mock
    private GridView gridView;

    @Mock
    private ResistancePath path;
    @Mock
    private Grid grid;


    @Before
    public void setUp() throws Exception {
        initMocks(this);

        doReturn(gridView).when(underTest).findViewById(input_grid);
        doReturn(intent).when(underTest).getIntent();
        doReturn("grid_column_count").when(underTest).getString(grid_column_count);
        doReturn("grid_row_count").when(underTest).getString(grid_row_count);
        when(intent.getIntExtra("grid_row_count", defaultValue)).thenReturn(rows);
        when(intent.getIntExtra("grid_column_count", defaultValue)).thenReturn(columns);
    }

    @Test
    public void shouldSetColumnCountToGridView() {
        underTest.setUpGridView();

        verify(gridView).setNumColumns(5);
    }

    @Test
    public void shouldSetAdapterToGridView() {
        doReturn(adapter).when(underTest).getEditTextAdapter();
        underTest.setUpGridView();

        verify(gridView).setAdapter(adapter);
    }

    @Test
    public void shoulReturnRowCount() {
        assertThat(underTest.getInputCount(grid_row_count), is(rows));
    }

    @Test
    public void shoulReturnColumnCount() {
        assertThat(underTest.getInputCount(grid_column_count), is(columns));
    }

    @Test
    public void shouldBeAEditTextAdapter() {
        assertThat(underTest.getEditTextAdapter(), isA(EditTextAdapter.class));
    }

    @Test
    public void shouldReturnYesIfCanFlow() {
        when(path.canFlow()).thenReturn(true);

        assertThat(underTest.canFlow(path), is("YES"));
    }

    @Test
    public void shouldReturnNoIfCanFlow() {
        when(path.canFlow()).thenReturn(false);

        assertThat(underTest.canFlow(path), is("NO"));
    }

    @Test
    public void shouldReturnResistancePathAsString() {
        when(path.getPath()).thenReturn(resistancePath);

        assertThat(underTest.getLeastResistancePath(path), is("1 1"));
    }

    @Test
    public void shouldReturnResistance() {
        when(path.getResistance()).thenReturn(resistance);

        assertThat(underTest.getLeastResistance(path), is("20"));
    }

}
