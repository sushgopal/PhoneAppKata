package com.phoneappkata.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static com.phoneappkata.activity.EditTextAdapter.CENTER_GRAVITY;
import static com.phoneappkata.activity.EditTextAdapter.SIGNED_NUMBER_INPUT_TYPE;
import static java.lang.String.valueOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class EditTextAdapterTest {
    private EditTextAdapter underTest;

    @Mock
    private GridInputActivity gridActivity;

    @Mock
    private ViewGroup noViewGroup;

    @Mock
    private EditText editTextView;

    private int rowCount = 4;

    private int columnCount = 5;

    private int position = 5 ;

    private View noView = null;

    @Before
    public void setup() {
        initMocks(this);
        underTest = spy(new EditTextAdapter(gridActivity, rowCount, columnCount));

        doReturn(editTextView).when(underTest).getNewEditTextView();
    }

    @Test
    public void shouldExtendBaseAdapter() {
        assertThat(underTest, isA(BaseAdapter.class));
    }

    @Test
    public void shouldReturnTotalCountOfCellsInGrid() {
        assertThat(underTest.getCount(), is(rowCount*columnCount));
    }

    @Test
    public void shouldReturnRowIndex() {
        assertThat(underTest.getItemId(position), is(1L));
    }

    @Test
    public void shouldReturnViewIfAlreadyCreated() {
        View result = createView();

        assertThat(result, Matchers.<View>is(editTextView));
    }

    @Test
    public void shouldCreateViewWithDefaultText() {
        createView();

        verify(editTextView).setText(valueOf(position));
    }

    @Test
    public void shouldCreateViewWithInputType() {
        createView();

        verify(editTextView).setInputType(SIGNED_NUMBER_INPUT_TYPE);
    }

    @Test
    public void shouldCreateViewWithGravity() {
        createView();

        verify(editTextView).setGravity(CENTER_GRAVITY);
    }

    @Test
    public void shouldCreateViewWithId() {
        createView();

        verify(editTextView).setId(eq(position));
    }

    private EditText createView() {
        return (EditText) underTest.getView(position, noView, noViewGroup);
    }
}
