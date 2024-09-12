package cifor.icraf.flashcardsxml.flashcard.ui.state

import cifor.icraf.flashcardsxml.flashcard.domain.relations.SubjectWithFlashcards

data class SubjectUIState(
    val isLoading: Boolean = false,
    val subjects: List<SubjectWithFlashcards> = emptyList(),
    val error: String? = null,
)