package com.phoneappkata;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class GridInputActivityTest {
    @Rule
    public IntentsTestRule<MainActivity> activityUnderTest = new IntentsTestRule(GridInputActivity.class);


}
