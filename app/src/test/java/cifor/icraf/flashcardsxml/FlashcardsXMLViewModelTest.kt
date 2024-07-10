package cifor.icraf.flashcardsxml

import cifor.icraf.flashcardsxml.flashcard.domain.entity.FlashcardEntity
import cifor.icraf.flashcardsxml.flashcard.domain.entity.SubjectEntity
import cifor.icraf.flashcardsxml.flashcard.domain.relations.SubjectWithFlashcards
import cifor.icraf.flashcardsxml.flashcard.domain.repository.FlashcardsXMLRepository
import cifor.icraf.flashcardsxml.flashcard.ui.viewmodel.FlashcardsXMLViewModel
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

class FlashcardsXMLViewModelTest {
    private val flashcardRepository = mockk<FlashcardsXMLRepository>(relaxed = true)
    private lateinit var flashcardViewModel: FlashcardsXMLViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher = Dispatchers.Unconfined)
        flashcardViewModel = FlashcardsXMLViewModel(flashcardsXMLRepository = flashcardRepository)
    }

    @Test
    fun getSubjectsWithFlashcards() = runTest {
        val subjectsWithFlashcards = listOf(
            SubjectWithFlashcards(
                subjectEntity = SubjectEntity(
                    subjectName = "Android"
                ),
                flashcards = listOf(
                    FlashcardEntity(
                        flashcardSubjectName = "Android",
                        flashCardTerm = "What is Android?",
                        flashCardDefinition = "Android is a mobile operating system developed by Google."
                    )
                )
            )
        )
        coEvery { flashcardRepository.getAllSubjectsWithFlashcards() } returns flowOf(value = subjectsWithFlashcards)
        flashcardViewModel.getSubjects()
        coVerify { flashcardRepository.getAllSubjectsWithFlashcards() }
        val uiState = flashcardViewModel.subjectUIState.value
        assertEquals(subjectsWithFlashcards, uiState.subjects)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}