package com.phoneappkata;

import android.content.Context;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.phoneappkata.activity.ResultActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.phoneappkata.R.id.can_flow;
import static com.phoneappkata.R.id.least_resistance;
import static com.phoneappkata.R.id.least_resistance_path;
import static com.phoneappkata.R.string.can_flow_result;
import static com.phoneappkata.R.string.least_resistance_path_result;
import static com.phoneappkata.R.string.least_resistance_result;

@RunWith(AndroidJUnit4.class)
public class ResultActivityUiTest {

    @Rule
    public ActivityTestRule<ResultActivity> activityUnderTest;

    private Intent intent;

    private String leastResistance = "12";

    private String canFlow = "YES";

    private String leastResistancePath = "path";

    private boolean initialTouchMode = false;

    private boolean launchActivity = false;

    @Before
    public void setup() {
        stubIntentWithResult();

        activityUnderTest = createActivityToTest();
        activityUnderTest.launchActivity(intent);
    }

    @Test
    public void shouldSetLeastResistanceInTextView() {
        onView(withId(least_resistance)).check(matches(withText(leastResistance)));
    }

    @Test
    public void shouldSetCanFlowInTextView() {
        onView(withId(can_flow)).check(matches(withText(canFlow)));
    }

    @Test
    public void shouldSetLeastResistancePathInTextView() {
        onView(withId(least_resistance_path)).check(matches(withText(leastResistancePath)));
    }

    private ActivityTestRule<ResultActivity> createActivityToTest() {
        return new ActivityTestRule<>(ResultActivity.class, initialTouchMode, launchActivity);
    }

    private void stubIntentWithResult() {
        Context context = getInstrumentation().getTargetContext();
        intent = new Intent(context, ResultActivity.class);

        intent.putExtra(context.getString(least_resistance_result), leastResistance);
        intent.putExtra(context.getString(can_flow_result), canFlow);
        intent.putExtra(context.getString(least_resistance_path_result), leastResistancePath);
    }
}
