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
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class AddNoteFlowWithParameterizedTest
                      (private val newTitle: String,
                       private val newDescription: String) {
    @get:Rule
    val rule: ActivityTestRule<MainActivity> =
            ActivityTestRule(MainActivity::class.java)

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() : List<Array<String>> {
            return listOf(
                    arrayOf("Title 1", "Description 1"),
                    arrayOf("Title 2", "Description 2")
            )
        }
    }

    @Test
    fun add_a_new_note_to_note_list() {
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
    }
}


