package cifor.icraf.flashcardsxml.flashcard.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cifor.icraf.flashcardsxml.flashcard.domain.entity.FlashcardEntity
import cifor.icraf.flashcardsxml.flashcard.domain.repository.FlashcardsXMLRepository
import kotlinx.coroutines.launch

class FlashcardsScreenViewModel(
    private val flashcardsXMLRepository: FlashcardsXMLRepository
) : ViewModel() {

    val flashcards: MutableLiveData<List<FlashcardEntity>> = MutableLiveData(emptyList())

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