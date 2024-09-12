package cifor.icraf.flashcardsxml

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import cifor.icraf.flashcardsxml.flashcard.ui.fragments.SubjectHomeScreenFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(value = AndroidJUnit4::class)
class SubjectHomeScreenFragmentTest {

    @Test
    fun subjectHomeScreenFragmentTopAppBarIsVisible() {
        launchFragmentInContainer<SubjectHomeScreenFragment>(
            themeResId = R.style.AppTheme
        )
        onView(withId(R.id.homeScreenToolbar)).check(matches(isDisplayed()))
    }

    @Test
    fun subjectHomeScreenFragmentTopAppBarTextIsVisible() {
        launchFragmentInContainer<SubjectHomeScreenFragment>(
            themeResId = R.style.AppTheme
        )
        onView(withId(R.id.homeScreenToolbar)).check(matches(hasDescendant(withText(R.string.app_name))))
    }

    @Test
    fun subjectHomeScreenFragmentFloatingActionButtonIsVisibleAndClickable() {
        launchFragmentInContainer<SubjectHomeScreenFragment>(
            themeResId = R.style.AppTheme
        )
        onView(withId(R.id.addSubjectButton)).check(matches(isDisplayed()))
        //onView(withId(R.id.addSubjectButton)).perform(click())
    }

}