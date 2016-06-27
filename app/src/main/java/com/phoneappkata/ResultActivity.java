package com.phoneappkata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        boolean canflow = intent.getBooleanExtra("canflow", false);
        int leastResistance = intent.getIntExtra("leastresistance", 0);
        ArrayList<Integer> path = intent.getIntegerArrayListExtra("leastresistancepath");//getIntegerArrayList("path");

        TextView t1 = (TextView) findViewById(R.id.did_flow_through);
        t1.setText(Boolean.toString(canflow));

        TextView t2 = (TextView) findViewById(R.id.least_resistance);
        t2.setText(leastResistance+"");

        TextView t3 = (TextView) findViewById(R.id.least_resistance_path);
        t3.setText(path.toString());

    }
}
