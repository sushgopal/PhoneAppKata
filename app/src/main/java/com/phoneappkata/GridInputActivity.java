package com.phoneappkata;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class GridInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_input);

        Intent intent = getIntent();

        int rows = intent.getIntExtra(MainActivity.NUMBER_OF_ROWS, 1);
        int columns = intent.getIntExtra(MainActivity.NUMBER_OF_COLUMNS, 5);

    }

}
