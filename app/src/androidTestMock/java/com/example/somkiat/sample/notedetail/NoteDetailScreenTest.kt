package com.example.somkiat.sample.notedetail

import android.content.Intent
import android.provider.ContactsContract.CommonDataKinds.Note.NOTE
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.example.somkiat.sample.R
import com.example.somkiat.sample.data.FakeNoteServiceApi
import com.example.somkiat.sample.data.Note
import com.example.somkiat.sample.notedetail.ImageViewHasDrawableMatcher.hasDrawable
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test

class NoteDetailScreenTest {
    @get:Rule
    private val rule: ActivityTestRule<NoteDetailActivity> =
            ActivityTestRule(NoteDetailActivity::class.java,
                    true,  // Initial touch mode
                    false) // Lazily launch activity

    @Test
    fun show_detail_of_note_in_screen() {
        // Arrange and Act
        val newNote = Note("Fake title", "Fake description");
        FakeNoteServiceApi.addNotes(newNote);
        val startIntent = Intent()
        startIntent.putExtra(NoteDetailActivity.EXTRA_NOTE_ID, newNote.id)
        rule.launchActivity(startIntent)

        // Assert
        onView(withId(R.id.note_detail_title))
                .check(matches(withText(newNote.title)))
        onView(withId(R.id.note_detail_description))
                .check(matches(withText(newNote.description)));
    }
}
