package com.phoneappkata;

import android.app.Activity;
import android.app.Instrumentation.ActivityResult;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.phoneappkata.R.id.submit_grid;
import static com.phoneappkata.R.string.can_flow_result;
import static com.phoneappkata.R.string.grid_column_count;
import static com.phoneappkata.R.string.grid_row_count;
import static com.phoneappkata.R.string.least_resistance_path_result;
import static com.phoneappkata.R.string.least_resistance_result;

@RunWith(AndroidJUnit4.class)
public class GridInputActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> activityUnderTest = new IntentsTestRule(GridInputActivity.class, true);

    @Test
    public void shouldCreateIntentForResultActivityOnSubmit() {
        onView(withId(submit_grid)).perform(click());

        intended(hasComponent("com.phoneappkata.ResultActivity"));
    }

    @Test
    public void shouldSetCanFlowToExtrasOnSubmit() {
        onView(withId(submit_grid)).perform(click());

        intended(hasExtraWithKey(getString(can_flow_result)));
    }

    @Test
    public void shouldSetLeastResistanceToExtrasOnSubmit() {
        onView(withId(submit_grid)).perform(click());

        intended(hasExtraWithKey(getString(least_resistance_result)));
    }

    @Test
    public void shouldSetLeastResistancePathToExtrasOnSubmit() {
        onView(withId(submit_grid)).perform(click());

        intended(hasExtraWithKey(getString(least_resistance_path_result)));
    }

    private String getString(int id) {
        return getTargetContext().getString(id);
    }
}
