package com.phoneappkata;

import android.content.Intent;
import android.view.View;
import android.widget.GridView;

import com.phoneappkata.leastresistancepath.Grid;
import com.phoneappkata.leastresistancepath.GridBuilder;
import com.phoneappkata.leastresistancepath.LeastResistancePath;
import com.phoneappkata.leastresistancepath.LeastResistancePathFinder;
import com.phoneappkata.leastresistancepath.ResistancePath;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;
import static com.phoneappkata.R.id.input_grid;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GridInputActivityUnitTest {
    @InjectMocks
    @Spy
    GridInputActivity underTest = new GridInputActivity();

    @Mock
    private Intent intent;
    @Mock
    private Intent resultIntent;
    @Mock
    private LeastResistancePath resultToShow;
    @Mock
    private android.os.Bundle bundle;
    @Mock
    private EditTextAdapter adapter;
    @Mock
    private GridView gridView;
    @Mock
    private ResistancePath path;
    @Mock
    private Grid grid;
    @Mock
    private GridBuilder gridBuilder;
    @Mock
    private View view;
    @Mock
    private LeastResistancePathFinder pathFinder;

    private final int resistance = 20;

    private final ArrayList<Integer> resistancePath = newArrayList(1, 1);

    private int defaultValue = 0;

    private int rows = 5;

    private int columns = 5;

    private final String grid_column_count = "grid_column_count";
    private final String grid_row_count = "grid_row_count";
    private final String can_flow_result = "can_flow_result";
    private final String least_resistance_result = "least_resistance_result";
    private final String least_resistance_path_result = "least_resistance_path_result";

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        stubFindViewById();
        stubGetString();
        stubIntent();
        stubUnderTest();
        stubGridBuilder();
        stubPathFinder();
    }

    @Test
    public void shouldSetColumnCountToGridView() {
        underTest.setUpGridView();

        verify(gridView).setNumColumns(5);
    }

    @Test
    public void shouldSetAdapterToGridView() {
        underTest.setUpGridView();

        verify(gridView).setAdapter(adapter);
    }

    @Test
    public void shoulReturnRowCount() {
        assertThat(underTest.getCount(R.string.grid_row_count), is(rows));
    }

    @Test
    public void shoulReturnColumnCount() {
        assertThat(underTest.getCount(R.string.grid_column_count), is(columns));
    }

    @Test
    public void shouldBeAEditTextAdapter() {
        assertThat(underTest.getEditTextAdapter(), isA(EditTextAdapter.class));
    }

    @Test
    public void shouldBuildGrid() {
        underTest.getLeastResistancePath(view);

        verify(gridBuilder).buildFrom(rows, columns, adapter);
    }

    @Test
    public void shouldGetPathFinderWithGrid() {
        underTest.getLeastResistancePath(view);

        verify(underTest).getPathFinder(grid);
    }

    @Test
    public void shouldCallFindLeastResistancePath() {
        underTest.getLeastResistancePath(view);

        verify(pathFinder).find();
    }

    @Test
    public void shouldPutCanFlowStringToResultIntentExtras() {
        underTest.getLeastResistancePath(view);

        verify(resultIntent).putExtra(can_flow_result, resultToShow.canFlow());
    }

    @Test
    public void shouldPutResistanceStringToResultIntentExtras() {
        underTest.getLeastResistancePath(view);

        verify(resultIntent).putExtra(least_resistance_result, resultToShow.resistance());
    }

    @Test
    public void shouldPutCanFlowDisplayStringToResultIntentExtras() {
        underTest.getLeastResistancePath(view);

        verify(resultIntent).putExtra(least_resistance_path_result, resultToShow.path());
    }

    private void stubPathFinder() {
        when(pathFinder.find()).thenReturn(path);
    }

    private void stubGridBuilder() {
        when(gridBuilder.buildFrom(rows, columns, adapter)).thenReturn(grid);
    }

    private void stubUnderTest() {
        doReturn(adapter).when(underTest).getEditTextAdapter();
        doReturn(pathFinder).when(underTest).getPathFinder(grid);
        doReturn(resultToShow).when(underTest).getResultToDisplayFrom(path);
        doReturn(resultIntent).when(underTest).getResultActivityIntent();
    }

    private void stubFindViewById() {
        doReturn(gridView).when(underTest).findViewById(input_grid);
    }

    private void stubGetString() {
        doReturn(grid_column_count).when(underTest).getString(R.string.grid_column_count);
        doReturn(grid_row_count).when(underTest).getString(R.string.grid_row_count);
        doReturn(can_flow_result).when(underTest).getString(R.string.can_flow_result);
        doReturn(least_resistance_result).when(underTest).getString(R.string.least_resistance_result);
        doReturn(least_resistance_path_result).when(underTest).getString(R.string.least_resistance_path_result);
    }

    private void stubIntent() {
        doReturn(intent).when(underTest).getIntent();
        when(intent.getIntExtra(grid_row_count, defaultValue)).thenReturn(rows);
        when(intent.getIntExtra(grid_column_count, defaultValue)).thenReturn(columns);
    }

}
