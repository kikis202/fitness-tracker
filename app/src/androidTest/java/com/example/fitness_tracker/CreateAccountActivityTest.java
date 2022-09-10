package com.example.fitness_tracker;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreateAccountActivityTest {

    @Rule
    public ActivityScenarioRule<SplashActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(SplashActivity.class);

    @Test
    public void createAccountActivityTest() {
        ViewInteraction textView = onView(
                allOf(withText("Let's get you started!"),
                        withParent(allOf(withId(R.id.greeting_layout),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        textView.check(matches(withText("Let's get you started!")));

        ViewInteraction textView2 = onView(
                allOf(withText("Welcome,"),
                        withParent(allOf(withId(R.id.greeting_layout),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        textView2.check(matches(withText("Welcome,")));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.sign_up_logo), withContentDescription("FitPlus logo"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.submit_sign_up_button), withText("CREATE ACCOUNT"),
                        withParent(allOf(withId(R.id.form_layout),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withText("Already have an account? "),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class))),
                        isDisplayed()));
        textView3.check(matches(withText("Already have an account? ")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.login_text_view_button), withText("Login"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class))),
                        isDisplayed()));
        textView4.check(matches(withText("Login")));

        ViewInteraction editText = onView(
                allOf(withId(R.id.username_edit_text), withText("Username"),
                        withParent(allOf(withId(R.id.form_layout),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        editText.check(matches(withText("Username")));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.password_edit_text), withText("Password"),
                        withParent(allOf(withId(R.id.form_layout),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        editText2.check(matches(withText("Password")));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.confirm_password_edit_text), withText("Confirm Password"),
                        withParent(allOf(withId(R.id.form_layout),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        editText3.check(matches(withText("Confirm Password")));

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.confirm_password_edit_text), withText("Confirm Password"),
                        withParent(allOf(withId(R.id.form_layout),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        editText4.check(matches(withText("Confirm Password")));

        pressBack();

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.login_text_view_button), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        3),
                                1)));
        materialTextView.perform(scrollTo(), click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
