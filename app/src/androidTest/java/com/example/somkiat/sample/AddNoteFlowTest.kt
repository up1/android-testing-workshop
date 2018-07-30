package com.example.somkiat.sample

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.scrollTo
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.text.TextUtils
import android.view.View
import com.google.common.base.Preconditions.checkArgument
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import android.support.v7.widget.RecyclerView
import android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA
import org.hamcrest.Description
import org.hamcrest.Matchers.allOf


class AddNoteFlowTest {
    @get:Rule
    val rule: ActivityTestRule<MainActivity> =
            ActivityTestRule(MainActivity::class.java)

    @Test
    fun click_add_not_button_should_open_add_note_screen() {
        // Act
        onView(withId(R.id.fab_add_notes))
                .perform(click())

        // Assert
        onView(withId(R.id.add_note_title))
                .check(matches(isDisplayed()))
        onView(withId(R.id.add_note_description))
                .check(matches(isDisplayed()))
    }

    @Test
    fun add_a_new_note_to_note_list() {
        val newTitle = "New title"
        val newDescription = "New description"

        // Click Add new note
        onView(withId(R.id.fab_add_notes))
                .perform(click())

        // Fill in data
        onView(withId(R.id.add_note_title))
                .perform(typeText(newTitle),
                         closeSoftKeyboard());
        onView(withId(R.id.add_note_description))
                .perform(typeText(newDescription),
                         closeSoftKeyboard());

        // Save note
        onView(withId(R.id.fab_add_notes)).perform(click());

        // Scroll to new note, by finding description
        onView(withId(R.id.notes_list))
                .perform(scrollTo<RecyclerView.ViewHolder>(
                        hasDescendant(withText(newDescription))));

        // Check description displayed on screen
        onView(withItemText(newDescription)).check(matches(isDisplayed()));
    }

    private fun withItemText(itemText: String): Matcher<View> {
        checkArgument(!TextUtils.isEmpty(itemText), "itemText cannot be null or empty")
        return object : TypeSafeMatcher<View>() {
            public override fun matchesSafely(item: View): Boolean {
                return allOf(
                        isDescendantOfA(isAssignableFrom(RecyclerView::class.java)),
                        withText(itemText)).matches(item)
            }

            override fun describeTo(description: Description) {
                description.appendText("is isDescendantOfA RV with text $itemText")
            }
        }
    }
}


