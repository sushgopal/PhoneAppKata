package com.phoneappkata;

import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;


public class EditTextAdapter<Integer> extends BaseAdapter {

    private GridInputActivity gridInputActivity;
    private int rowCount;
    private int columnCount;

    public EditTextAdapter(GridInputActivity gridInputActivity, int rowCount, int columnCount) {
        this.gridInputActivity = gridInputActivity;
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }

    @Override
    public int getCount() {
        return rowCount*columnCount;
    }

    @Override
    public Object getItem(int position) {
        EditText viewById = (EditText) gridInputActivity.findViewById(position);
        Editable value = viewById.getText();
        return value;
    }

    @Override
    public long getItemId(int position) {
        return position / columnCount;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        EditText text;
        if(view == null) {
            text = new EditText(gridInputActivity);
            text.setText(String.valueOf(position));
            int inputType = InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED;
            text.setInputType(inputType);
            text.setGravity(0x11);
            text.setId(position);
        }
        else {
            text = (EditText)view;
        }
        return text;
    }
}
