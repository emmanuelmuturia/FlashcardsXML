package cifor.icraf.flashcardsxml

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
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
import cifor.icraf.flashcardsxml.flashcard.ui.fragments.SubjectHomeScreenFragment
import cifor.icraf.flashcardsxml.main.activity.MainActivity
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(value = AndroidJUnit4::class)
@LargeTest
class SubjectHomeScreenFragmentTest {

    private lateinit var fragmentScenario: FragmentScenario<SubjectHomeScreenFragment>

    @Before
    fun setup() {
        fragmentScenario = launchFragmentInContainer(themeResId = R.style.AppTheme)
        fragmentScenario.moveToState(newState = Lifecycle.State.STARTED)
    }

    @Test
    fun topAppBarIsVisible() {
        onView(withId(R.id.homeScreenToolbar)).check(matches(isDisplayed()))
    }

    @Test
    fun topAppBarTextIsVisible() {
        onView(withId(R.id.homeScreenToolbar)).check(matches(hasDescendant(withText(R.string.app_name))))
    }

    /*@Test
    fun floatingActionButtonIsVisibleAndClickable() {
        onView(withId(R.id.addSubjectButton)).perform(click())
    }*/

}