package com.phoneappkata.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static android.view.Gravity.CENTER;
import static com.phoneappkata.R.id.can_flow;
import static com.phoneappkata.R.id.least_resistance;
import static com.phoneappkata.R.id.least_resistance_path;
import static com.phoneappkata.R.layout.activity_result;
import static com.phoneappkata.R.string.can_flow_result;
import static com.phoneappkata.R.string.least_resistance_path_result;
import static com.phoneappkata.R.string.least_resistance_result;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_result);

        setTextToView(can_flow, can_flow_result);
        setTextToView(least_resistance, least_resistance_result);
        setTextToView(least_resistance_path, least_resistance_path_result);
    }

    private void setTextToView(int viewId, int textId) {
        TextView t1 = getTextViewFrom(viewId);
        t1.setText(getTextFromIntentWith(textId));
        t1.setGravity(CENTER);
    }

    private TextView getTextViewFrom(int can_flow) {
        return (TextView) findViewById(can_flow);
    }

    private String getTextFromIntentWith(int resourceId) {
        return getIntent().getStringExtra(getString(resourceId));
    }
}
