package com.phoneappkata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

import com.phoneappkata.leastresistancepath.Grid;
import com.phoneappkata.leastresistancepath.LeastResistancePathFinder;
import com.phoneappkata.leastresistancepath.ResistancePath;

import static com.phoneappkata.R.layout.activity_grid_input;
import static com.phoneappkata.R.string.grid_column_count;
import static com.phoneappkata.R.string.grid_row_count;

public class GridInputActivity extends AppCompatActivity {

    private int[][] gridArray;

    EditTextAdapter<Integer> editTextAdapter;

    private int rowCount;

    private int columnCount;

    private static int DEFAULT_VALUE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_grid_input);

        Intent intent = getIntent();

        rowCount = intent.getIntExtra(getString(grid_row_count), DEFAULT_VALUE);
        columnCount = intent.getIntExtra(getString(grid_column_count), DEFAULT_VALUE);

        GridView grid=(GridView) findViewById(R.id.input_grid);
        grid.setNumColumns(columnCount);

        editTextAdapter=new EditTextAdapter<Integer> (this, rowCount, columnCount);
        grid.setAdapter(editTextAdapter);
    }

    public void getLeastResistancePath(View view) {
        gridArray = new int[rowCount][columnCount];

        for(int i=0;i<rowCount;i++) {
            for(int j=0; j<columnCount; j++) {
                Object value = editTextAdapter.getItem((columnCount * i) + j);
                Integer intValue = Integer.valueOf(value.toString());
                gridArray[i][j] = intValue;
            }
        }
        LeastResistancePathFinder finder = new LeastResistancePathFinder(new Grid(gridArray));
        ResistancePath result = finder.find();

        Intent intent = new Intent(this, ResultActivity.class);
//        ResistancePath result = null;
        intent.putExtra("canflow", result.canFlow() ? "YES": "NO");
        intent.putExtra("leastresistance", Integer.toString(result.getResistance()));
        intent.putExtra("leastresistancepath", result.getPath().toString());

        startActivity(intent);

    }

}
