package com.phoneappkata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import static com.phoneappkata.R.string.grid_column_count;
import static com.phoneappkata.R.string.grid_row_count;

public class MainActivity extends AppCompatActivity {

    private NumberPicker rowPicker;

    private NumberPicker columnPicker;

    private NumberPickerFactory factory = new NumberPickerFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        callOnCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rowPicker = factory.createRowPicker(this);
        columnPicker = factory.createColumnPicker(this);
    }

    void callOnCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void submitGridSize(View view) {
        Intent intent = getGridInputActivity();

        intent.putExtra(getString(grid_row_count), getGridRowCount());
        intent.putExtra(getString(grid_column_count), getGridColumnCount());

        startActivity(intent);
    }

    private int getGridRowCount() {
        return rowPicker.getValue();
    }

    private int getGridColumnCount() {
        return columnPicker.getValue();
    }

    private Intent getGridInputActivity() {
        return new Intent(this, GridInputActivity.class);
    }
}
