package cifor.icraf.flashcardsxml

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import cifor.icraf.flashcardsxml.main.activity.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(value = AndroidJUnit4::class)
@LargeTest
class SubjectEditScreenFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun toolBarExists() {
        onView(withId(R.id.addSubjectButton)).perform(click())
        onView(withId(R.id.subjectEditScreenToolbar)).check(matches(isDisplayed()))
    }

    @Test
    fun toolBarHasClickableButtons() {
        onView(withId(R.id.addSubjectButton)).perform(click())

        onView(withId(R.id.subjectEditScreenToolbar)).check(matches(hasDescendant((withId(R.id.subjectEditScreenBackButton)))))
            .perform(click())

        onView(withId(R.id.subjectEditScreenToolbar)).check(matches(hasDescendant(withId(R.id.subjectEditScreenDoneButton))))
            .perform(
                click()
            )
    }

    @Test
    fun textViewExists() {
        onView(withId(R.id.addSubjectButton)).perform(click())
        onView(withId(R.id.editTextSubjectName)).check(matches(isDisplayed()))
    }

}