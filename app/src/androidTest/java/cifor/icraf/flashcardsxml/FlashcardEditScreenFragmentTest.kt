package cifor.icraf.flashcardsxml

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import cifor.icraf.flashcardsxml.flashcard.ui.fragments.FlashcardEditScreenFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(value = AndroidJUnit4::class)
@LargeTest
class FlashcardEditScreenFragmentTest {

    private lateinit var fragmentScenario: FragmentScenario<FlashcardEditScreenFragment>

    @Before
    fun setup() {
        val fragmentArgs = bundleOf("subjectName" to "Android")
        fragmentScenario = launchFragmentInContainer(fragmentArgs = fragmentArgs, themeResId = R.style.AppTheme)
        fragmentScenario.moveToState(newState = Lifecycle.State.STARTED)
    }

    @Test
    fun toolBarExists() {
        onView(withId(R.id.flashcardsEditScreenTopAppBar)).check(matches(isDisplayed()))
    }

    @Test
    fun toolBarHasClickableButtons() {
        onView(withId(R.id.flashcardsEditScreenTopAppBar)).check(matches(hasDescendant((withId(R.id.flashcardsEditScreenBackButton)))))
            .perform(click())

        onView(withId(R.id.flashcardsEditScreenTopAppBar)).check(matches(hasDescendant(withId(R.id.flashcardsEditScreenDoneButton))))
            .perform(
                click()
            )
    }

    @Test
    fun textViewsExist() {
        onView(withId(R.id.flashcardTermEditText)).check(matches(isDisplayed()))
        onView(withId(R.id.flashcardDefinitionEditText)).check(matches(isDisplayed()))
    }

}