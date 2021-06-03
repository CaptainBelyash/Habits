package com.example.habitsapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.example.habitsapp.Screens.EditorScreen
import com.example.habitsapp.Screens.MainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import java.lang.AssertionError

@RunWith(AndroidJUnit4::class)
class CreateHabitScreenUITest: TestCase() {
    private var editorScreen = EditorScreen()
    @get:Rule
    public val activityTestRule =
        ActivityTestRule(MainActivity::class.java, true, false)

    @Before
    fun setUp(){
        activityTestRule.launchActivity(null)
        onScreen<MainScreen>{createHabitButton.click()}
    }

    @Test
    fun deleteButton_shouldNotBeDisplayed_atFirstTime() {
        editorScreen.deleteHabitButton.isNotDisplayed()
    }

    @Test
    fun allFields_shouldNotFilled_atStart() {
        editorScreen.nameField.hasEmptyText()
        editorScreen.descriptionField.hasEmptyText()
    }

    @Test
    fun allFields_shouldFilledCorrectly() {
        editorScreen.nameField.click()
        editorScreen.nameField.click()
        editorScreen.nameField.click()
        editorScreen.nameField.typeText("habit")
        editorScreen.descriptionField.click()
        editorScreen.descriptionField.typeText("description")
    }
}