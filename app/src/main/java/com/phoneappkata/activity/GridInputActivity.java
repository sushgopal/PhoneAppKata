package com.phoneappkata.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

import com.phoneappkata.leastresistancepath.Grid;
import com.phoneappkata.leastresistancepath.GridBuilder;
import com.phoneappkata.leastresistancepath.LeastResistancePath;
import com.phoneappkata.leastresistancepath.LeastResistancePathFinder;
import com.phoneappkata.leastresistancepath.ResistancePath;

import static com.phoneappkata.R.id.input_grid;
import static com.phoneappkata.R.layout.activity_grid_input;
import static com.phoneappkata.R.string.can_flow_result;
import static com.phoneappkata.R.string.grid_column_count;
import static com.phoneappkata.R.string.grid_row_count;
import static com.phoneappkata.R.string.least_resistance_path_result;
import static com.phoneappkata.R.string.least_resistance_result;

public class GridInputActivity extends AppCompatActivity {

    private static int DEFAULT_VALUE = 0;

    private EditTextAdapter<Integer> editTextAdapter;

    private GridBuilder gridBuilder = new GridBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_grid_input);

        setUpGridView();
    }

    void setUpGridView() {
        editTextAdapter= getEditTextAdapter();

        GridView grid=(GridView) findViewById(input_grid);
        grid.setNumColumns(getCount(grid_column_count));
        grid.setAdapter(editTextAdapter);
    }

    EditTextAdapter<Integer> getEditTextAdapter() {
        return new EditTextAdapter<>(this, getCount(grid_row_count), getCount(grid_column_count));
    }

    int getCount(int id) {
        return getIntent().getIntExtra(getString(id), DEFAULT_VALUE);
    }

    public void getLeastResistancePath(View view) {
        ResistancePath result = getPathFinder(getGrid()).find();
        LeastResistancePath leastResistancePath = getResultToDisplayFrom(result);

        startResultActivity(leastResistancePath);
    }

    LeastResistancePathFinder getPathFinder(Grid grid) {
        return new LeastResistancePathFinder(grid);
    }

    private Grid getGrid() {
        return gridBuilder.buildFrom(getCount(grid_row_count), getCount(grid_column_count), editTextAdapter);
    }

    LeastResistancePath getResultToDisplayFrom(ResistancePath result) {
        return new LeastResistancePath(result);
    }

    private void startResultActivity(LeastResistancePath leastResistancePath) {
        Intent intent = getResultActivityIntent();

        intent.putExtra(getString(can_flow_result), leastResistancePath.canFlow());
        intent.putExtra(getString(least_resistance_result), leastResistancePath.resistance());
        intent.putExtra(getString(least_resistance_path_result), leastResistancePath.path());

        startActivity(intent);
    }

    Intent getResultActivityIntent() {
        return new Intent(this, ResultActivity.class);
    }

}
