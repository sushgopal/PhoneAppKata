package com.phoneappkata;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.NumberPicker;
import android.os.Bundle;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

//    @InjectMocks
//    @Spy
//    MainActivity underTest;

//    @Mock
//    NumberPicker rowPicker;
//
//    @Mock
//    NumberPicker columnPicker;
//
//    @Mock
//    private Bundle bundle;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }

//    @Test
//    public void shouldCallOnCreateOfAppCompatActivity() {
//        underTest.onCreate(bundle);
//
//        Mockito.verify(underTest).callOnCreate(bundle);
//    }

//    @Test
//    public void shouldSetMinValueOneForRowPicker() {
//        onView(withId(R.id.row_picker)).check()
//    }

}
