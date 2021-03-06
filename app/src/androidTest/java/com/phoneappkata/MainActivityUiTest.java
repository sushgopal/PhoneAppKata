package com.phoneappkata;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.phoneappkata.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.*;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.BundleMatchers.hasEntry;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtras;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.phoneappkata.R.id.column_picker;
import static com.phoneappkata.R.id.row_picker;
import static com.phoneappkata.R.id.submit_grid_size;
import static com.phoneappkata.R.string.grid_column_count;
import static com.phoneappkata.R.string.grid_row_count;


@RunWith(AndroidJUnit4.class)
public class MainActivityUiTest {

    @Rule
    public IntentsTestRule<MainActivity> activityUnderTest = new IntentsTestRule(MainActivity.class);

    @Test
    public void shouldHaveRowPickerToChooseRow() {
        onView(withId(row_picker)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldHaveColumnPickerToChooseColumn() {
        onView(withId(column_picker)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldCreateIntentForGridInputActivityOnSubmit() {
        onView(withId(submit_grid_size)).perform(click());

        intended(hasComponent("com.phoneappkata.activity.GridInputActivity"));
    }

    @Test
    public void shouldSetGridRowCountToExtrasOnSubmit() {
        onView(withId(submit_grid_size)).perform(click());

        intended(hasExtras(hasEntry(getString(grid_column_count), 5)));
    }

    @Test
    public void shouldSetGridColumnCountToExtrasOnSubmit() {
        onView(withId(submit_grid_size)).perform(click());

        intended(hasExtras(hasEntry(getString(grid_row_count), 1)));
    }

    private String getString(int id) {
        return getTargetContext().getString(id);
    }
}
