package cifor.icraf.flashcardsxml

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import cifor.icraf.flashcardsxml.flashcard.ui.fragments.SubjectEditScreenFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(value = AndroidJUnit4::class)
class SubjectEditScreenFragmentTest {

    @Test
    fun subjectEditScreenFragmentToolBarExists() {
        launchFragmentInContainer<SubjectEditScreenFragment>(
            themeResId = R.style.AppTheme
        )
        onView(withId(R.id.subjectEditScreenToolbar)).check(matches(isDisplayed()))
    }

    @Test
    fun subjectEditScreenFragmentTextViewExists() {
        launchFragmentInContainer<SubjectEditScreenFragment>(
            themeResId = R.style.AppTheme
        )
        onView(withId(R.id.editTextSubjectName)).check(matches(isDisplayed()))
    }

    @Test
    fun subjectEditScreenFragmentTextViewEntersValue() {
        launchFragmentInContainer<SubjectEditScreenFragment>(
            themeResId = R.style.AppTheme
        )
        val subjectName = "Android"
        onView(withId(R.id.editTextSubjectName)).perform(typeText(subjectName))
    }

}