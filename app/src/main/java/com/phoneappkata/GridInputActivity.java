package com.phoneappkata;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.phoneappkata.leastresistancepath.LeastResistancePath;
import com.phoneappkata.leastresistancepath.LeastResistancePathFinder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GridInputActivity extends AppCompatActivity {

    private int[][] gridArray;

    EditTextAdapter<Integer> editTextAdapter;

    int rowCount;

    int columnCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_input);

        Intent intent = getIntent();

        rowCount = intent.getIntExtra(MainActivity.NUMBER_OF_ROWS, 1);
        columnCount = intent.getIntExtra(MainActivity.NUMBER_OF_COLUMNS, 5);

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
        LeastResistancePathFinder finder = new LeastResistancePathFinder();
        LeastResistancePath result = finder.find(gridArray);

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("canflow", result.canFlow() ? "YES": "NO");
        intent.putExtra("leastresistance", Integer.toString(result.getResistance()));
        intent.putExtra("leastresistancepath", result.getPath().toString());

        startActivity(intent);

    }

}
