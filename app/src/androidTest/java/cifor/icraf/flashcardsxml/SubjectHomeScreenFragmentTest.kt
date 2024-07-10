package cifor.icraf.flashcardsxml

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import cifor.icraf.flashcardsxml.main.activity.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(value = AndroidJUnit4::class)
@LargeTest
class SubjectHomeScreenFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun topAppBarIsVisible() {
        onView(withId(R.id.homeScreenToolbar)).check(matches(isDisplayed()))
    }

    @Test
    fun topAppBarTextIsVisible() {
        onView(withId(R.id.homeScreenToolbar)).check(matches(hasDescendant(withText(R.string.app_name))))
    }

    @Test
    fun floatingActionButtonIsVisibleAndClickable() {
        onView(withId(R.id.addSubjectButton)).perform(click())
    }

}