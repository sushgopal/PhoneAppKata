package com.phoneappkata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

import com.phoneappkata.leastresistancepath.Grid;
import com.phoneappkata.leastresistancepath.GridBuilder;
import com.phoneappkata.leastresistancepath.LeastResistancePathFinder;
import com.phoneappkata.leastresistancepath.ResistancePath;

import org.apache.commons.lang3.StringUtils;

import static com.phoneappkata.R.id.input_grid;
import static com.phoneappkata.R.layout.activity_grid_input;
import static com.phoneappkata.R.string.can_flow_result;
import static com.phoneappkata.R.string.grid_column_count;
import static com.phoneappkata.R.string.grid_row_count;
import static com.phoneappkata.R.string.least_resistance_path_result;
import static com.phoneappkata.R.string.least_resistance_result;

public class GridInputActivity extends AppCompatActivity {

    private char DELIMITER = ' ';

    private int rowCount;

    private int columnCount;

    private static int DEFAULT_VALUE = 0;

    private static String YES = "YES";

    private static String NO = "NO";

    private EditTextAdapter<Integer> editTextAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_grid_input);

        rowCount = getInputCount(grid_row_count);
        columnCount = getInputCount(grid_column_count);

        setUpGridView();
    }

    public void getLeastResistancePath(View view) {
        ResistancePath result = getPathFinder(getGrid()).find();

        startResultActivity(result);
    }

    private void startResultActivity(ResistancePath result) {
        Intent intent = getResultActivityIntent();

        intent.putExtra(getString(can_flow_result), canFlow(result));
        intent.putExtra(getString(least_resistance_result), getLeastResistance(result));
        intent.putExtra(getString(least_resistance_path_result), getLeastResistancePath(result));

        startActivity(intent);
    }

    private Grid getGrid() {
        return new GridBuilder().buildFrom(rowCount, columnCount, editTextAdapter);
    }

    void setUpGridView() {
        editTextAdapter= getEditTextAdapter();

        GridView grid=(GridView) findViewById(input_grid);
        grid.setNumColumns(getInputCount(grid_column_count));
        grid.setAdapter(editTextAdapter);
    }

    EditTextAdapter<Integer> getEditTextAdapter() {
        return new EditTextAdapter<>(this, rowCount, columnCount);
    }

    int getInputCount(int id) {
        return getIntent().getIntExtra(getString(id), DEFAULT_VALUE);
    }

    private LeastResistancePathFinder getPathFinder(Grid grid) {
        return new LeastResistancePathFinder(grid);
    }

    String getLeastResistancePath(ResistancePath result) {
        return StringUtils.join(result.getPath(), DELIMITER);
    }

    String getLeastResistance(ResistancePath result) {
        return Integer.toString(result.getResistance());
    }

    String canFlow(ResistancePath result) {
        return result.canFlow() ? YES: NO;
    }

    Intent getResultActivityIntent() {
        return new Intent(this, ResultActivity.class);
    }

}
