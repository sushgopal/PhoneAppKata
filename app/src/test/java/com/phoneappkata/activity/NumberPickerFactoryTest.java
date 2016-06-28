package com.phoneappkata.activity;

import android.widget.NumberPicker;

import com.phoneappkata.activity.MainActivity;
import com.phoneappkata.activity.NumberPickerFactory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static com.phoneappkata.R.id.column_picker;
import static com.phoneappkata.R.id.row_picker;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class NumberPickerFactoryTest {

    private NumberPickerFactory underTest;

    @Mock
    private MainActivity activity;

    @Mock
    private NumberPicker columnNumberPicker;

    @Mock
    private NumberPicker rowNumberPicker;

    private int minRow = 1;

    private int maxRow = 10;

    private int minColumn = 5;

    private int maxColumn = 100;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        underTest = new NumberPickerFactory();

        stubActivity();
    }

    private void stubActivity() {
        when(activity.findViewById(column_picker)).thenReturn(columnNumberPicker);
        when(activity.findViewById(row_picker)).thenReturn(rowNumberPicker);
    }

    @Test
    public void shouldSetDefaultColumnValue() {
        underTest.createColumnPicker(activity);

        verify(columnNumberPicker).setValue(minColumn);
    }

    @Test
    public void shouldSetDefaultRowValue() {
        underTest.createRowPicker(activity);

        verify(rowNumberPicker).setValue(minRow);
    }

    @Test
    public void shouldSetMinColumnValue() {
        underTest.createColumnPicker(activity);

        verify(columnNumberPicker).setMinValue(minColumn);
    }

    @Test
    public void shouldSetMinRowValue() {
        underTest.createRowPicker(activity);

        verify(rowNumberPicker).setMinValue(minRow);
    }

    @Test
    public void shouldSetMaxColumnValue() {
        underTest.createColumnPicker(activity);

        verify(columnNumberPicker).setMaxValue(maxColumn);
    }

    @Test
    public void shouldSetMaxRowValue() {
        underTest.createRowPicker(activity);

        verify(rowNumberPicker).setMaxValue(maxRow);
    }
}
