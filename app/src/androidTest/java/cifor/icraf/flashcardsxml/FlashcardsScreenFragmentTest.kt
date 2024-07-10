package cifor.icraf.flashcardsxml

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import cifor.icraf.flashcardsxml.flashcard.ui.fragments.FlashcardsScreenFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(value = AndroidJUnit4::class)
@LargeTest
class FlashcardsScreenFragmentTest {

    private lateinit var fragmentScenario: FragmentScenario<FlashcardsScreenFragment>

    @Before
    fun setup() {
        val fragmentBundles = bundleOf("subjectName" to "Android")
        fragmentScenario = launchFragmentInContainer(themeResId = R.style.AppTheme, fragmentArgs = fragmentBundles)
        fragmentScenario.moveToState(newState = Lifecycle.State.STARTED)
    }

    @Test
    fun toolBarExists() {
        onView(withId(R.id.flashcardsScreenTopAppBar)).check(matches(isDisplayed()))
    }

    /*@Test
    fun floatingActionButtonExists() {
        onView(withId(R.id.addFlashcardButton)).check(matches(isDisplayed())).perform(click())
    }*/

}