package cifor.icraf.flashcardsxml.flashcard.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cifor.icraf.flashcardsxml.flashcard.domain.entity.FlashcardEntity
import cifor.icraf.flashcardsxml.flashcard.domain.repository.FlashcardsXMLRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FlashcardsScreenViewModel(
    private val flashcardsXMLRepository: FlashcardsXMLRepository
) : ViewModel() {

    val flashcards: MutableStateFlow<List<FlashcardEntity>> = MutableStateFlow(value = emptyList())

    init {
        getAllFlashcards()
    }

    fun getAllFlashcards() {
        viewModelScope.launch {
            flashcardsXMLRepository.getAllFlashcards().collect { flashcardList ->
                flashcards.value = flashcardList
            }
        }
    }

}