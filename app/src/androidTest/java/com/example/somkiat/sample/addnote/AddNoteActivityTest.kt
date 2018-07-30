package com.example.somkiat.sample.addnote

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

class AddNoteActivityTest {

    @get:Rule
    val rule: ActivityTestRule<AddNoteActivity> =
            ActivityTestRule(AddNoteActivity::class.java,
                    false,
                    false)

    @Test
    fun first() {
        rule.launchActivity(Intent());
    }

}