package com.example.fitness_tracker;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
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
public class ExerciseListTest {

    @Rule
    public ActivityScenarioRule<SplashActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(SplashActivity.class);

    @Test
    public void exerciseListTest() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.submit_sign_up_button), withText("CREATE ACCOUNT"),
                        childAtPosition(
                                allOf(withId(R.id.form_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                3)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.username_edit_text),
                        childAtPosition(
                                allOf(withId(R.id.form_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                0)));
        appCompatEditText.perform(scrollTo(), click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.username_edit_text),
                        childAtPosition(
                                allOf(withId(R.id.form_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                0)));
        appCompatEditText2.perform(scrollTo(), replaceText("daniils3"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.username_edit_text), withText("daniils3"),
                        childAtPosition(
                                allOf(withId(R.id.form_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                0)));
        appCompatEditText3.perform(pressImeActionButton());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.password_edit_text),
                        childAtPosition(
                                allOf(withId(R.id.form_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                1)));
        appCompatEditText4.perform(scrollTo(), replaceText("1234567"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.password_edit_text), withText("1234567"),
                        childAtPosition(
                                allOf(withId(R.id.form_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                1)));
        appCompatEditText5.perform(pressImeActionButton());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.confirm_password_edit_text),
                        childAtPosition(
                                allOf(withId(R.id.form_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                2)));
        appCompatEditText6.perform(scrollTo(), replaceText("1234567"), closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.confirm_password_edit_text), withText("1234567"),
                        childAtPosition(
                                allOf(withId(R.id.form_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                2)));
        appCompatEditText7.perform(pressImeActionButton());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.submit_sign_up_button), withText("CREATE ACCOUNT"),
                        childAtPosition(
                                allOf(withId(R.id.form_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                3)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.username_edit_text),
                        childAtPosition(
                                allOf(withId(R.id.form_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                0)));
        appCompatEditText8.perform(scrollTo(), replaceText("daniils3"), closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.username_edit_text), withText("daniils3"),
                        childAtPosition(
                                allOf(withId(R.id.form_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                0)));
        appCompatEditText9.perform(pressImeActionButton());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.password_edit_text),
                        childAtPosition(
                                allOf(withId(R.id.form_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                1)));
        appCompatEditText10.perform(scrollTo(), replaceText("1234567"), closeSoftKeyboard());

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.password_edit_text), withText("1234567"),
                        childAtPosition(
                                allOf(withId(R.id.form_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                1)));
        appCompatEditText11.perform(pressImeActionButton());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.submit_login_button), withText("LOGIN"),
                        childAtPosition(
                                allOf(withId(R.id.form_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                2)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.linear_layout_button),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                1),
                        isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction linearLayout2 = onView(
                allOf(withId(R.id.add_exercise_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        linearLayout2.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.firstEx), withText("BACK EXTENSION"),
                        withParent(withParent(withId(R.id.backExtension))),
                        isDisplayed()));
        textView.check(matches(withText("BACK EXTENSION")));

        ViewInteraction textView2 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.backExtension))),
                        isDisplayed()));
        textView2.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView = onView(
                allOf(withParent(withParent(withId(R.id.backExtension))),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction tableRow = onView(
                allOf(withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        tableRow.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withText("BENCH PRESS"),
                        withParent(withParent(withId(R.id.benchPress))),
                        isDisplayed()));
        textView3.check(matches(withText("BENCH PRESS")));

        ViewInteraction textView4 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.benchPress))),
                        isDisplayed()));
        textView4.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView2 = onView(
                allOf(withParent(withParent(withId(R.id.benchPress))),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction textView5 = onView(
                allOf(withText("BENT OVER ROW BARBELL"),
                        withParent(withParent(withId(R.id.bentOverRowBarbell))),
                        isDisplayed()));
        textView5.check(matches(withText("BENT OVER ROW BARBELL")));

        ViewInteraction textView6 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.bentOverRowBarbell))),
                        isDisplayed()));
        textView6.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView3 = onView(
                allOf(withParent(withParent(withId(R.id.bentOverRowBarbell))),
                        isDisplayed()));
        imageView3.check(matches(isDisplayed()));

        ViewInteraction textView7 = onView(
                allOf(withText("BICYCLE CRUNCHES"),
                        withParent(withParent(withId(R.id.bicycleCrunches))),
                        isDisplayed()));
        textView7.check(matches(withText("BICYCLE CRUNCHES")));

        ViewInteraction textView8 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.bicycleCrunches))),
                        isDisplayed()));
        textView8.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView4 = onView(
                allOf(withParent(withParent(withId(R.id.bicycleCrunches))),
                        isDisplayed()));
        imageView4.check(matches(isDisplayed()));

        ViewInteraction textView9 = onView(
                allOf(withText("CONCENTRATION CURLS"),
                        withParent(withParent(withId(R.id.concentrationCurls))),
                        isDisplayed()));
        textView9.check(matches(withText("CONCENTRATION CURLS")));

        ViewInteraction textView10 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.concentrationCurls))),
                        isDisplayed()));
        textView10.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView5 = onView(
                allOf(withParent(withParent(withId(R.id.concentrationCurls))),
                        isDisplayed()));
        imageView5.check(matches(isDisplayed()));

        ViewInteraction imageView6 = onView(
                allOf(withParent(withParent(withId(R.id.concentrationCurls))),
                        isDisplayed()));
        imageView6.check(matches(isDisplayed()));

        ViewInteraction textView11 = onView(
                allOf(withText("CRUNCHES"),
                        withParent(withParent(withId(R.id.crunches))),
                        isDisplayed()));
        textView11.check(matches(withText("CRUNCHES")));

        ViewInteraction textView12 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.crunches))),
                        isDisplayed()));
        textView12.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView7 = onView(
                allOf(withParent(withParent(withId(R.id.crunches))),
                        isDisplayed()));
        imageView7.check(matches(isDisplayed()));

        ViewInteraction textView13 = onView(
                allOf(withText("DIPS"),
                        withParent(withParent(withId(R.id.dips))),
                        isDisplayed()));
        textView13.check(matches(withText("DIPS")));

        ViewInteraction textView14 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.dips))),
                        isDisplayed()));
        textView14.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView8 = onView(
                allOf(withParent(withParent(withId(R.id.dips))),
                        isDisplayed()));
        imageView8.check(matches(isDisplayed()));

        ViewInteraction textView15 = onView(
                allOf(withText("DUMBBELL CURL"),
                        withParent(withParent(withId(R.id.dumbbellCurl))),
                        isDisplayed()));
        textView15.check(matches(withText("DUMBBELL CURL")));

        ViewInteraction textView16 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.dumbbellCurl))),
                        isDisplayed()));
        textView16.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView9 = onView(
                allOf(withParent(withParent(withId(R.id.dumbbellCurl))),
                        isDisplayed()));
        imageView9.check(matches(isDisplayed()));

        ViewInteraction textView17 = onView(
                allOf(withText("DUMBBELL FLY"),
                        withParent(withParent(withId(R.id.dumbbellFly))),
                        isDisplayed()));
        textView17.check(matches(withText("DUMBBELL FLY")));

        ViewInteraction textView18 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.dumbbellFly))),
                        isDisplayed()));
        textView18.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView10 = onView(
                allOf(withParent(withParent(withId(R.id.dumbbellFly))),
                        isDisplayed()));
        imageView10.check(matches(isDisplayed()));

        ViewInteraction textView19 = onView(
                allOf(withText("DUMBBELL BENCH PRESS"),
                        withParent(withParent(withId(R.id.dumbbellBenchPress))),
                        isDisplayed()));
        textView19.check(matches(withText("DUMBBELL BENCH PRESS")));

        ViewInteraction textView20 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.dumbbellBenchPress))),
                        isDisplayed()));
        textView20.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView11 = onView(
                allOf(withParent(withParent(withId(R.id.dumbbellBenchPress))),
                        isDisplayed()));
        imageView11.check(matches(isDisplayed()));

        ViewInteraction imageView12 = onView(
                allOf(withParent(withParent(withId(R.id.dumbbellBenchPress))),
                        isDisplayed()));
        imageView12.check(matches(isDisplayed()));

        ViewInteraction textView21 = onView(
                allOf(withText("HANGING KNEE RAISE"),
                        withParent(withParent(withId(R.id.hangingKneeRaise))),
                        isDisplayed()));
        textView21.check(matches(withText("HANGING KNEE RAISE")));

        ViewInteraction textView22 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.hangingKneeRaise))),
                        isDisplayed()));
        textView22.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView13 = onView(
                allOf(withParent(withParent(withId(R.id.hangingKneeRaise))),
                        isDisplayed()));
        imageView13.check(matches(isDisplayed()));

        ViewInteraction textView23 = onView(
                allOf(withText("INCLINE BENCH PRESS"),
                        withParent(withParent(withId(R.id.inclineBenchPress))),
                        isDisplayed()));
        textView23.check(matches(withText("INCLINE BENCH PRESS")));

        ViewInteraction textView24 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.inclineBenchPress))),
                        isDisplayed()));
        textView24.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView14 = onView(
                allOf(withParent(withParent(withId(R.id.inclineBenchPress))),
                        isDisplayed()));
        imageView14.check(matches(isDisplayed()));

        ViewInteraction textView25 = onView(
                allOf(withText("LATERAL DUMBBELL RAISES"),
                        withParent(withParent(withId(R.id.lateralDumbbellRaises))),
                        isDisplayed()));
        textView25.check(matches(withText("LATERAL DUMBBELL RAISES")));

        ViewInteraction textView26 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.lateralDumbbellRaises))),
                        isDisplayed()));
        textView26.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView15 = onView(
                allOf(withParent(withParent(withId(R.id.lateralDumbbellRaises))),
                        isDisplayed()));
        imageView15.check(matches(isDisplayed()));

        ViewInteraction textView27 = onView(
                allOf(withText("LEG CURL"),
                        withParent(withParent(withId(R.id.legCurl))),
                        isDisplayed()));
        textView27.check(matches(withText("LEG CURL")));

        ViewInteraction textView28 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.legCurl))),
                        isDisplayed()));
        textView28.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView16 = onView(
                allOf(withParent(withParent(withId(R.id.legCurl))),
                        isDisplayed()));
        imageView16.check(matches(isDisplayed()));

        ViewInteraction textView29 = onView(
                allOf(withText("LEG EXTENSION"),
                        withParent(withParent(withId(R.id.legExtension))),
                        isDisplayed()));
        textView29.check(matches(withText("LEG EXTENSION")));

        ViewInteraction textView30 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.legExtension))),
                        isDisplayed()));
        textView30.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView17 = onView(
                allOf(withParent(withParent(withId(R.id.legExtension))),
                        isDisplayed()));
        imageView17.check(matches(isDisplayed()));

        ViewInteraction imageView18 = onView(
                allOf(withParent(withParent(withId(R.id.legExtension))),
                        isDisplayed()));
        imageView18.check(matches(isDisplayed()));

        ViewInteraction textView31 = onView(
                allOf(withText("LEG PRESS"),
                        withParent(withParent(withId(R.id.legPress))),
                        isDisplayed()));
        textView31.check(matches(withText("LEG PRESS")));

        ViewInteraction textView32 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.legPress))),
                        isDisplayed()));
        textView32.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView19 = onView(
                allOf(withParent(withParent(withId(R.id.legPress))),
                        isDisplayed()));
        imageView19.check(matches(isDisplayed()));

        ViewInteraction textView33 = onView(
                allOf(withText("LEG RAISES"),
                        withParent(withParent(withId(R.id.legRaises))),
                        isDisplayed()));
        textView33.check(matches(withText("LEG RAISES")));

        ViewInteraction textView34 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.legRaises))),
                        isDisplayed()));
        textView34.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView20 = onView(
                allOf(withParent(withParent(withId(R.id.legRaises))),
                        isDisplayed()));
        imageView20.check(matches(isDisplayed()));

        ViewInteraction textView35 = onView(
                allOf(withText("OVERHEAD TRICEPS PRESS CABLE WITH BAR"),
                        withParent(withParent(withId(R.id.overheadTricepsPress))),
                        isDisplayed()));
        textView35.check(matches(withText("OVERHEAD TRICEPS PRESS CABLE WITH BAR")));

        ViewInteraction textView36 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.overheadTricepsPress))),
                        isDisplayed()));
        textView36.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView21 = onView(
                allOf(withParent(withParent(withId(R.id.overheadTricepsPress))),
                        isDisplayed()));
        imageView21.check(matches(isDisplayed()));

        ViewInteraction textView37 = onView(
                allOf(withText("PLANK"),
                        withParent(withParent(withId(R.id.plank))),
                        isDisplayed()));
        textView37.check(matches(withText("PLANK")));

        ViewInteraction textView38 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.plank))),
                        isDisplayed()));
        textView38.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView22 = onView(
                allOf(withParent(withParent(withId(R.id.plank))),
                        isDisplayed()));
        imageView22.check(matches(isDisplayed()));

        ViewInteraction textView39 = onView(
                allOf(withText("PREACHER CURL"),
                        withParent(withParent(withId(R.id.preacherCurl))),
                        isDisplayed()));
        textView39.check(matches(withText("PREACHER CURL")));

        ViewInteraction textView40 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.preacherCurl))),
                        isDisplayed()));
        textView40.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView23 = onView(
                allOf(withParent(withParent(withId(R.id.preacherCurl))),
                        isDisplayed()));
        imageView23.check(matches(isDisplayed()));

        ViewInteraction imageView24 = onView(
                allOf(withParent(withParent(withId(R.id.preacherCurl))),
                        isDisplayed()));
        imageView24.check(matches(isDisplayed()));

        ViewInteraction textView41 = onView(
                allOf(withText("PULL DOWN STOCK"),
                        withParent(withParent(withId(R.id.pullDownStock))),
                        isDisplayed()));
        textView41.check(matches(withText("PULL DOWN STOCK")));

        ViewInteraction textView42 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.pullDownStock))),
                        isDisplayed()));
        textView42.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView25 = onView(
                allOf(withParent(withParent(withId(R.id.pullDownStock))),
                        isDisplayed()));
        imageView25.check(matches(isDisplayed()));

        ViewInteraction textView43 = onView(
                allOf(withText("PULL UP"),
                        withParent(withParent(withId(R.id.pullUp))),
                        isDisplayed()));
        textView43.check(matches(withText("PULL UP")));

        ViewInteraction textView44 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.pullUp))),
                        isDisplayed()));
        textView44.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView26 = onView(
                allOf(withParent(withParent(withId(R.id.pullUp))),
                        isDisplayed()));
        imageView26.check(matches(isDisplayed()));

        ViewInteraction textView45 = onView(
                allOf(withText("PUSH UP"),
                        withParent(withParent(withId(R.id.pushUp))),
                        isDisplayed()));
        textView45.check(matches(withText("PUSH UP")));

        ViewInteraction textView46 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.pushUp))),
                        isDisplayed()));
        textView46.check(matches(withText("Choose this exercise")));

        ViewInteraction textView47 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.pushUp))),
                        isDisplayed()));
        textView47.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView27 = onView(
                allOf(withParent(withParent(withId(R.id.pushUp))),
                        isDisplayed()));
        imageView27.check(matches(isDisplayed()));

        ViewInteraction textView48 = onView(
                allOf(withText("REAR DELT RAISE"),
                        withParent(withParent(withId(R.id.rearDeltRaise))),
                        isDisplayed()));
        textView48.check(matches(withText("REAR DELT RAISE")));

        ViewInteraction textView49 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.rearDeltRaise))),
                        isDisplayed()));
        textView49.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView28 = onView(
                allOf(withParent(withParent(withId(R.id.rearDeltRaise))),
                        isDisplayed()));
        imageView28.check(matches(isDisplayed()));

        ViewInteraction textView50 = onView(
                allOf(withText("SEATED ROW MACHINE"),
                        withParent(withParent(withId(R.id.seatedRowMachine))),
                        isDisplayed()));
        textView50.check(matches(withText("SEATED ROW MACHINE")));

        ViewInteraction textView51 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.seatedRowMachine))),
                        isDisplayed()));
        textView51.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView29 = onView(
                allOf(withParent(withParent(withId(R.id.seatedRowMachine))),
                        isDisplayed()));
        imageView29.check(matches(isDisplayed()));

        ViewInteraction imageView30 = onView(
                allOf(withParent(withParent(withId(R.id.seatedRowMachine))),
                        isDisplayed()));
        imageView30.check(matches(isDisplayed()));

        ViewInteraction textView52 = onView(
                allOf(withText("SHOULDER PRESS"),
                        withParent(withParent(withId(R.id.shoulderPress))),
                        isDisplayed()));
        textView52.check(matches(withText("SHOULDER PRESS")));

        ViewInteraction textView53 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.shoulderPress))),
                        isDisplayed()));
        textView53.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView31 = onView(
                allOf(withParent(withParent(withId(R.id.shoulderPress))),
                        isDisplayed()));
        imageView31.check(matches(isDisplayed()));

        ViewInteraction textView54 = onView(
                allOf(withText("SIDE PLANK"),
                        withParent(withParent(withId(R.id.sidePlank))),
                        isDisplayed()));
        textView54.check(matches(withText("SIDE PLANK")));

        ViewInteraction textView55 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.sidePlank))),
                        isDisplayed()));
        textView55.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView32 = onView(
                allOf(withParent(withParent(withId(R.id.sidePlank))),
                        isDisplayed()));
        imageView32.check(matches(isDisplayed()));

        ViewInteraction textView56 = onView(
                allOf(withText("SQUATS"),
                        withParent(withParent(withId(R.id.squats))),
                        isDisplayed()));
        textView56.check(matches(withText("SQUATS")));

        ViewInteraction textView57 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.squats))),
                        isDisplayed()));
        textView57.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView33 = onView(
                allOf(withParent(withParent(withId(R.id.squats))),
                        isDisplayed()));
        imageView33.check(matches(isDisplayed()));

        ViewInteraction textView58 = onView(
                allOf(withText("STRAIGHT BAR CURL"),
                        withParent(withParent(withId(R.id.straightBarCurl))),
                        isDisplayed()));
        textView58.check(matches(withText("STRAIGHT BAR CURL")));

        ViewInteraction textView59 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.straightBarCurl))),
                        isDisplayed()));
        textView59.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView34 = onView(
                allOf(withParent(withParent(withId(R.id.straightBarCurl))),
                        isDisplayed()));
        imageView34.check(matches(isDisplayed()));

        ViewInteraction textView60 = onView(
                allOf(withText("STRAIGHT LEG DEADLIFT"),
                        withParent(withParent(withId(R.id.straightLegDeadlifts))),
                        isDisplayed()));
        textView60.check(matches(withText("STRAIGHT LEG DEADLIFT")));

        ViewInteraction textView61 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.straightLegDeadlifts))),
                        isDisplayed()));
        textView61.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView35 = onView(
                allOf(withParent(withParent(withId(R.id.straightLegDeadlifts))),
                        isDisplayed()));
        imageView35.check(matches(isDisplayed()));

        ViewInteraction imageView36 = onView(
                allOf(withParent(withParent(withId(R.id.straightLegDeadlifts))),
                        isDisplayed()));
        imageView36.check(matches(isDisplayed()));

        ViewInteraction textView62 = onView(
                allOf(withText("SIT UPS"),
                        withParent(withParent(withId(R.id.sitUps))),
                        isDisplayed()));
        textView62.check(matches(withText("SIT UPS")));

        ViewInteraction textView63 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.sitUps))),
                        isDisplayed()));
        textView63.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView37 = onView(
                allOf(withParent(withParent(withId(R.id.sitUps))),
                        isDisplayed()));
        imageView37.check(matches(isDisplayed()));

        ViewInteraction textView64 = onView(
                allOf(withText("TRICEP KICKBACKS"),
                        withParent(withParent(withId(R.id.tricepKickbacks))),
                        isDisplayed()));
        textView64.check(matches(withText("TRICEP KICKBACKS")));

        ViewInteraction textView65 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.tricepKickbacks))),
                        isDisplayed()));
        textView65.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView38 = onView(
                allOf(withParent(withParent(withId(R.id.tricepKickbacks))),
                        isDisplayed()));
        imageView38.check(matches(isDisplayed()));

        ViewInteraction textView66 = onView(
                allOf(withText("TRICEP PRESS"),
                        withParent(withParent(withId(R.id.tricepPress))),
                        isDisplayed()));
        textView66.check(matches(withText("TRICEP PRESS")));

        ViewInteraction textView67 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.tricepPress))),
                        isDisplayed()));
        textView67.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView39 = onView(
                allOf(withParent(withParent(withId(R.id.tricepPress))),
                        isDisplayed()));
        imageView39.check(matches(isDisplayed()));

        ViewInteraction textView68 = onView(
                allOf(withText("TRICEP PULLOVERS"),
                        withParent(withParent(withId(R.id.tricepPullovers))),
                        isDisplayed()));
        textView68.check(matches(withText("TRICEP PULLOVERS")));

        ViewInteraction textView69 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.tricepPullovers))),
                        isDisplayed()));
        textView69.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView40 = onView(
                allOf(withParent(withParent(withId(R.id.tricepPullovers))),
                        isDisplayed()));
        imageView40.check(matches(isDisplayed()));

        ViewInteraction textView70 = onView(
                allOf(withText("TRICEP PULLOVERS WITH DUMBBELL"),
                        withParent(withParent(withId(R.id.tricepPulloversWithDumbbell))),
                        isDisplayed()));
        textView70.check(matches(withText("TRICEP PULLOVERS WITH DUMBBELL")));

        ViewInteraction textView71 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.tricepPulloversWithDumbbell))),
                        isDisplayed()));
        textView71.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView41 = onView(
                allOf(withParent(withParent(withId(R.id.tricepPulloversWithDumbbell))),
                        isDisplayed()));
        imageView41.check(matches(isDisplayed()));

        ViewInteraction imageView42 = onView(
                allOf(withParent(withParent(withId(R.id.tricepPulloversWithDumbbell))),
                        isDisplayed()));
        imageView42.check(matches(isDisplayed()));

        ViewInteraction textView72 = onView(
                allOf(withText("UPRIGHT ROWS"),
                        withParent(withParent(withId(R.id.uprightRows))),
                        isDisplayed()));
        textView72.check(matches(withText("UPRIGHT ROWS")));

        ViewInteraction textView73 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.uprightRows))),
                        isDisplayed()));
        textView73.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView43 = onView(
                allOf(withParent(withParent(withId(R.id.uprightRows))),
                        isDisplayed()));
        imageView43.check(matches(isDisplayed()));

        ViewInteraction textView74 = onView(
                allOf(withText("WALKING LUNGES DUMBBELLS"),
                        withParent(withParent(withId(R.id.walkingLungesDumbbells))),
                        isDisplayed()));
        textView74.check(matches(withText("WALKING LUNGES DUMBBELLS")));

        ViewInteraction textView75 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.walkingLungesDumbbells))),
                        isDisplayed()));
        textView75.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView44 = onView(
                allOf(withParent(withParent(withId(R.id.walkingLungesDumbbells))),
                        isDisplayed()));
        imageView44.check(matches(isDisplayed()));

        ViewInteraction textView76 = onView(
                allOf(withText("WEIGHTED CALF RAISES"),
                        withParent(withParent(withId(R.id.weightedCalfRaises))),
                        isDisplayed()));
        textView76.check(matches(withText("WEIGHTED CALF RAISES")));

        ViewInteraction textView77 = onView(
                allOf(withText("Choose this exercise"),
                        withParent(withParent(withId(R.id.weightedCalfRaises))),
                        isDisplayed()));
        textView77.check(matches(withText("Choose this exercise")));

        ViewInteraction imageView45 = onView(
                allOf(withParent(withParent(withId(R.id.weightedCalfRaises))),
                        isDisplayed()));
        imageView45.check(matches(isDisplayed()));

        ViewInteraction imageView46 = onView(
                allOf(withParent(withParent(withId(R.id.weightedCalfRaises))),
                        isDisplayed()));
        imageView46.check(matches(isDisplayed()));

        ViewInteraction linearLayout3 = onView(
                allOf(withId(R.id.weightedCalfRaises),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                74)));
        linearLayout3.perform(scrollTo(), click());
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
