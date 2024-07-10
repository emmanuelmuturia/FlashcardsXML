package cifor.icraf.flashcardsxml

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import cifor.icraf.flashcardsxml.flashcard.ui.fragments.SubjectEditScreenFragment
import cifor.icraf.flashcardsxml.main.activity.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(value = AndroidJUnit4::class)
@LargeTest
class SubjectEditScreenFragmentTest {

    private lateinit var fragmentScenario: FragmentScenario<SubjectEditScreenFragment>

    @Before
    fun setup() {
        fragmentScenario = launchFragmentInContainer(themeResId = R.style.AppTheme)
        fragmentScenario.moveToState(newState = Lifecycle.State.STARTED)
    }

    @Test
    fun toolBarExists() {
        onView(withId(R.id.subjectEditScreenToolbar)).check(matches(isDisplayed()))
    }

    @Test
    fun toolBarHasClickableButtons() {
        onView(withId(R.id.subjectEditScreenToolbar)).check(matches(hasDescendant((withId(R.id.subjectEditScreenBackButton)))))
            .perform(click())

        onView(withId(R.id.subjectEditScreenToolbar)).check(matches(hasDescendant(withId(R.id.subjectEditScreenDoneButton))))
            .perform(
                click()
            )
    }

    @Test
    fun textViewExists() {
        onView(withId(R.id.editTextSubjectName)).check(matches(isDisplayed()))
    }

    @Test
    fun textViewEntersValue() {
        val subjectName = "Android"
        onView(withId(R.id.editTextSubjectName)).perform(typeText(subjectName))
    }

}