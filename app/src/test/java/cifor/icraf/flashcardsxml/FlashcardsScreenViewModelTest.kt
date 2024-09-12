package cifor.icraf.flashcardsxml

import cifor.icraf.flashcardsxml.flashcard.domain.entity.FlashcardEntity
import cifor.icraf.flashcardsxml.flashcard.domain.entity.SubjectEntity
import cifor.icraf.flashcardsxml.flashcard.domain.relations.SubjectWithFlashcards
import cifor.icraf.flashcardsxml.flashcard.domain.repository.FlashcardsXMLRepository
import cifor.icraf.flashcardsxml.flashcard.ui.viewmodel.FlashcardsScreenViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FlashcardsScreenViewModelTest {

    private val flashcardsXMLRepository: FlashcardsXMLRepository = mockk(relaxed = true)
    private lateinit var flashcardsScreenViewModel: FlashcardsScreenViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher = Dispatchers.Unconfined)
        flashcardsScreenViewModel = FlashcardsScreenViewModel(
            flashcardsXMLRepository = flashcardsXMLRepository
        )
    }

    @Test
    fun getAllFlashcards() = runTest {

        val flashcards = listOf(
            FlashcardEntity(
                flashcardSubjectName = "Android",
                flashCardTerm = "Android",
                flashCardDefinition = "Android is a mobile operating system developed by Google."
            ),
            FlashcardEntity(
                flashcardSubjectName = "Android",
                flashCardTerm = "Activity",
                flashCardDefinition = "It is an Android component that represents a User Interface (UI)..."
            ),
            FlashcardEntity(
                flashcardSubjectName = "Android",
                flashCardTerm = "Intent",
                flashCardDefinition = "It is used to start an Activity..."
            )
        )
        coEvery { flashcardsXMLRepository.getAllFlashcards() } returns flowOf(value = flashcards)
        flashcardsScreenViewModel.getAllFlashcards()
        coVerify { flashcardsXMLRepository.getAllFlashcards() }
        val flashcardsState = flashcardsScreenViewModel.flashcards.value
        assertEquals(flashcards, flashcardsState)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}