package com.phoneappkata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static android.view.Gravity.CENTER;
import static com.phoneappkata.R.id.can_flow;
import static com.phoneappkata.R.id.least_resistance;
import static com.phoneappkata.R.id.least_resistance_path;
import static com.phoneappkata.R.string.can_flow_result;
import static com.phoneappkata.R.string.least_resistance_path_result;
import static com.phoneappkata.R.string.least_resistance_result;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setTextToTextView(can_flow, can_flow_result);
        setTextToTextView(least_resistance, least_resistance_result);
        setTextToTextView(least_resistance_path, least_resistance_path_result);
    }

    private void setTextToTextView(int textViewId, int textResourceId) {
        TextView t1 = getTextViewFrom(textViewId);
        t1.setText(getFromIntent(textResourceId));
        t1.setGravity(CENTER);
    }

    private TextView getTextViewFrom(int can_flow) {
        return (TextView) findViewById(can_flow);
    }

    private String getFromIntent(int resourceId) {
        return getIntent().getStringExtra(getString(resourceId));
    }
}
