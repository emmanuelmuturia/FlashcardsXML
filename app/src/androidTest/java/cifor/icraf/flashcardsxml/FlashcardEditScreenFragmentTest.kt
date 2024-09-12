package cifor.icraf.flashcardsxml

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import cifor.icraf.flashcardsxml.flashcard.ui.fragments.FlashcardEditScreenFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(value = AndroidJUnit4::class)
class FlashcardEditScreenFragmentTest {

    @Test
    fun flashcardEditScreenFragmentToolBarExists() {
        launchFragmentInContainer<FlashcardEditScreenFragment>(
            themeResId = R.style.AppTheme
        )
        onView(withId(R.id.flashcardsEditScreenTopAppBar)).check(matches(isDisplayed()))
    }

    @Test
    fun flashcardEditScreenFragmentTextViewsExist() {
        launchFragmentInContainer<FlashcardEditScreenFragment>(
            themeResId = R.style.AppTheme
        )
        onView(withId(R.id.flashcardTermEditText)).check(matches(isDisplayed()))
        onView(withId(R.id.flashcardDefinitionEditText)).check(matches(isDisplayed()))
    }

}