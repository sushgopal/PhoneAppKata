package com.phoneappkata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {

    static final String NUMBER_OF_ROWS = "NUMBER_OF_ROWS";

    static final String NUMBER_OF_COLUMNS = "NUMBER_OF_COLUMNS";

    private NumberPicker rowPicker;

    private NumberPicker columnPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rowPicker = (NumberPicker) findViewById(R.id.row_picker);
        rowPicker.setMinValue(1);
        rowPicker.setMaxValue(10);
        rowPicker.setValue(1);

        columnPicker = (NumberPicker) findViewById(R.id.column_picker);
        columnPicker.setValue(5);
        columnPicker.setMinValue(5);
        columnPicker.setMaxValue(100);
    }

    public void submitGridSize(View view) {
        Intent intent = new Intent(this, GridInputActivity.class);
        intent.putExtra(NUMBER_OF_ROWS, rowPicker.getValue());
        intent.putExtra(NUMBER_OF_COLUMNS, columnPicker.getValue());
        startActivity(intent);
    }
}
