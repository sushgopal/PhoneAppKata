package com.phoneappkata.activity;

import android.widget.NumberPicker;

import com.phoneappkata.activity.MainActivity;

import static com.phoneappkata.R.id.column_picker;
import static com.phoneappkata.R.id.row_picker;

public class NumberPickerFactory {

    private static int MINIMUM_ROW = 1;

    private static int MAXIMUM_ROW = 10;

    private static int MINIMUM_COLUMN = 5;

    private static int MAXIMUM_COLUMN = 100;


    public NumberPicker createColumnPicker(MainActivity mainActivity) {
        return createForId(mainActivity, column_picker, MINIMUM_COLUMN, MAXIMUM_COLUMN);
    }

    public NumberPicker createRowPicker(MainActivity mainActivity) {
        return createForId(mainActivity, row_picker, MINIMUM_ROW, MAXIMUM_ROW);
    }

    private NumberPicker createForId(MainActivity mainActivity, int viewId, int min, int max) {
        NumberPicker numberPicker = (NumberPicker) mainActivity.findViewById(viewId);

        numberPicker.setValue(min);
        numberPicker.setMinValue(min);
        numberPicker.setMaxValue(max);

        return numberPicker;
    }
}
