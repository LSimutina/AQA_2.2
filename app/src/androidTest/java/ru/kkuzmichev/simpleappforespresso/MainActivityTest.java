package ru.kkuzmichev.simpleappforespresso;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction textView = onView(withId(R.id.text_home));
        textView.check(matches(isDisplayed()));
        textView.check(matches(withText("This is home fragment")));

        ViewInteraction appCompatImageButton = onView(withContentDescription("Open navigation drawer"));
        appCompatImageButton.check(matches(isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction checkedTextView = onView(
                allOf(withId(androidx.navigation.ui.R.id.design_menu_item_text), withText("Home"),
                        withParent(allOf(withId(R.id.nav_home),
                                withParent(withId(androidx.navigation.ui.R.id.design_navigation_view)))),
                        isDisplayed()));
        checkedTextView.check(matches(isDisplayed()));

        ViewInteraction checkedTextView2 = onView(
                allOf(withId(androidx.navigation.ui.R.id.design_menu_item_text), withText("Gallery"),
                        withParent(allOf(withId(R.id.nav_gallery),
                                withParent(withId(androidx.navigation.ui.R.id.design_navigation_view)))),
                        isDisplayed()));
        checkedTextView2.check(matches(isDisplayed()));

        ViewInteraction checkedTextView3 = onView(
                allOf(withId(androidx.navigation.ui.R.id.design_menu_item_text), withText("Slideshow"),
                        withParent(allOf(withId(R.id.nav_slideshow),
                                withParent(withId(androidx.navigation.ui.R.id.design_navigation_view)))),
                        isDisplayed()));
        checkedTextView3.check(matches(isDisplayed()));

        ViewInteraction navigationMenuItemView = onView(withId(R.id.nav_slideshow));
        navigationMenuItemView.check(matches(isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction textView2 = onView(withId(R.id.text_slideshow));
        textView2.check(matches(isDisplayed()));
        textView2.check(matches(withText("This is slideshow fragment")));
    }
}
