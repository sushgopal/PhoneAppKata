package com.phoneappkata;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_NUMBER_FLAG_SIGNED;
import static java.lang.String.valueOf;


public class EditTextAdapter<Integer> extends BaseAdapter {

    static int GRAVITY = 0x11;

    static int SIGNED_NUMBER_INPUT_TYPE = TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_SIGNED;

    private GridInputActivity gridActivity;

    private int rowCount;

    private int columnCount;

    public EditTextAdapter(GridInputActivity activity, int rowCount, int columnCount) {
        this.gridActivity = activity;
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }

    @Override
    public int getCount() {
        return rowCount * columnCount;
    }

    @Override
    public Object getItem(int position) {
        return getView(position).getText();
    }

    @Override
    public long getItemId(int position) {
        return (position / columnCount);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view != null) {
            return view;
        }

        EditText text = getEditText();

        text.setText(valueOf(position));
        text.setInputType(SIGNED_NUMBER_INPUT_TYPE);
        text.setGravity(GRAVITY);
        text.setId(position);

        return text;
    }

    private EditText getView(int position) {
        return (EditText) gridActivity.findViewById(position);
    }

    EditText getEditText() {
        return new EditText(gridActivity);
    }
}
