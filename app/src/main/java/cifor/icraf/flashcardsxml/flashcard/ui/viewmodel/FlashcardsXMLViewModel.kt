package cifor.icraf.flashcardsxml.flashcard.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cifor.icraf.flashcardsxml.commons.result.LocalResult
import cifor.icraf.flashcardsxml.commons.result.asResult
import cifor.icraf.flashcardsxml.flashcard.domain.entity.FlashcardEntity
import cifor.icraf.flashcardsxml.flashcard.domain.entity.SubjectEntity
import cifor.icraf.flashcardsxml.flashcard.domain.repository.FlashcardsXMLRepository
import cifor.icraf.flashcardsxml.flashcard.ui.state.SubjectUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FlashcardsXMLViewModel(
    private val flashcardsXMLRepository: FlashcardsXMLRepository
) : ViewModel() {
    val subjectUIState: MutableStateFlow<SubjectUIState> =
        MutableStateFlow(value = SubjectUIState())

    init {
        getSubjects()
    }

    fun getSubjects() {
        subjectUIState.value = SubjectUIState(isLoading = true)
        viewModelScope.launch {
            flashcardsXMLRepository.getAllFlashcardsBySubject().asResult().collect { result ->
                when (result) {
                    is LocalResult.Success -> {
                        subjectUIState.update {
                            it.copy(isLoading = false, subjects = result.data)
                        }
                    }

                    is LocalResult.Error -> {
                        subjectUIState.update {
                            it.copy(isLoading = false, error = result.errorMessage)
                        }
                    }
                }
            }
        }
    }

    fun searchForSubject(searchQuery: String) {
        viewModelScope.launch {
            flashcardsXMLRepository.searchForSubject(searchQuery = searchQuery)
                .collect { subjectWithFlashcards ->
                    subjectUIState.update {
                        it.copy(
                            subjects = subjectWithFlashcards,
                        )
                    }
                }
        }
    }

    fun upsertSubject(subjectEntity: SubjectEntity) {
        viewModelScope.launch {
            flashcardsXMLRepository.upsertSubject(subjectEntity = subjectEntity)
        }
    }

    fun upsertFlashcard(flashcardEntity: FlashcardEntity) {
        viewModelScope.launch {
            flashcardsXMLRepository.upsertFlashcard(flashcardEntity = flashcardEntity)
        }
    }

    fun deleteSubject(subjectEntity: SubjectEntity) {
        viewModelScope.launch {
            flashcardsXMLRepository.deleteSubject(subjectEntity = subjectEntity)
        }
    }

    fun deleteFlashcard(flashcardEntity: FlashcardEntity) {
        viewModelScope.launch {
            flashcardsXMLRepository.deleteFlashcard(flashcardEntity = flashcardEntity)
        }
    }
}