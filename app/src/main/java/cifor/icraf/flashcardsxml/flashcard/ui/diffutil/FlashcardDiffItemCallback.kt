package cifor.icraf.flashcardsxml.flashcard.ui.diffutil

import androidx.recyclerview.widget.DiffUtil
import cifor.icraf.flashcardsxml.flashcard.domain.entity.FlashcardEntity

class FlashcardDiffItemCallback : DiffUtil.ItemCallback<FlashcardEntity>() {

    override fun areItemsTheSame(oldItem: FlashcardEntity, newItem: FlashcardEntity): Boolean {
        return oldItem.flashCardTerm == newItem.flashCardTerm
    }

    override fun areContentsTheSame(oldItem: FlashcardEntity, newItem: FlashcardEntity): Boolean {
        return (oldItem == newItem)
    }

}