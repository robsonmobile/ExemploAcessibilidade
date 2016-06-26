package net.paulacr.loginaccessibility;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

/**
 * Created by paularosa on 5/22/16.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void hasContentDescriptionLabelOnGoogleImage() {

        //testar se o componente possui contentDescription
        onView(withId(R.id.google))
                .check(matches(withContentDescription("logar com google")));
    }

    @Test
    public void hasContentDescriptionLabelOnFacebookImage() {
        //testar se o componente possui contentDescription
        onView(withId(R.id.facebook))
                .check(matches(withContentDescription("logar com facebook")));
    }

    @Test
    public void hasNullContentDescription() {
        //testar se o componente possui o @null
        onView(withId(R.id.coins))
                .check(matches(not(hasContentDescription())));
    }

}
