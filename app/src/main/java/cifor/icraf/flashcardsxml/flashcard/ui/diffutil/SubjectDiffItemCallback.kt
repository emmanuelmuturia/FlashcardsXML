package cifor.icraf.flashcardsxml.flashcard.ui.diffutil

import androidx.recyclerview.widget.DiffUtil
import cifor.icraf.flashcardsxml.flashcard.domain.relations.SubjectWithFlashcards

class SubjectDiffItemCallback : DiffUtil.ItemCallback<SubjectWithFlashcards>() {

    override fun areItemsTheSame(
        oldItem: SubjectWithFlashcards,
        newItem: SubjectWithFlashcards
    ): Boolean {
        return oldItem.subjectEntity == newItem.subjectEntity
    }

    override fun areContentsTheSame(
        oldItem: SubjectWithFlashcards,
        newItem: SubjectWithFlashcards
    ): Boolean {
        return (oldItem == newItem)
    }

}