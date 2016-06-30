package com.phoneappkata;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.phoneappkata.activity.GridInputActivity;
import com.phoneappkata.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.phoneappkata.R.id.submit_grid;
import static com.phoneappkata.R.string.can_flow_result;
import static com.phoneappkata.R.string.least_resistance_path_result;
import static com.phoneappkata.R.string.least_resistance_result;

@RunWith(AndroidJUnit4.class)
public class GridInputActivityUiTest {

    @Rule
    public IntentsTestRule<MainActivity> activityUnderTest = new IntentsTestRule(GridInputActivity.class, true);

    @Test
    public void shouldCreateIntentForResultActivityOnSubmit() {
        onView(withId(submit_grid)).perform(click());

        intended(hasComponent("com.phoneappkata.activity.ResultActivity"));
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
