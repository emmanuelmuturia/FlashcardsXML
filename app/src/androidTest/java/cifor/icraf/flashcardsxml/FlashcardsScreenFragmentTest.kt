package cifor.icraf.flashcardsxml

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import cifor.icraf.flashcardsxml.flashcard.ui.fragments.FlashcardsScreenFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(value = AndroidJUnit4::class)
class FlashcardsScreenFragmentTest {

    @Test
    fun flashcardsScreenFragmentToolBarExists() {
        launchFragmentInContainer<FlashcardsScreenFragment>(
            themeResId = R.style.AppTheme,
            fragmentArgs = bundleOf("subjectName" to "Android")
        )
        onView(withId(R.id.flashcardsScreenTopAppBar)).check(matches(isDisplayed()))
    }

    @Test
    fun flashcardsScreenFragmentFloatingActionButtonExistsAndIsClickable() {
        launchFragmentInContainer<FlashcardsScreenFragment>(
            themeResId = R.style.AppTheme,
            fragmentArgs = bundleOf("subjectName" to "Android")
        )
        onView(withId(R.id.addFlashcardButton)).check(matches(isDisplayed()))
    }

}