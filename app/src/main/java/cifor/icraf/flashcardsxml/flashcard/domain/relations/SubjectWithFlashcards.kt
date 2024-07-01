package cifor.icraf.flashcardsxml.flashcard.domain.relations

import androidx.room.Embedded
import androidx.room.Relation
import cifor.icraf.flashcardsxml.flashcard.domain.entity.FlashcardEntity
import cifor.icraf.flashcardsxml.flashcard.domain.entity.SubjectEntity
import kotlinx.serialization.Serializable

data class SubjectWithFlashcards(
    @Embedded val subjectEntity: SubjectEntity,
    @Relation(
        parentColumn = "subjectName",
        entityColumn = "flashcardSubjectName",
    )
    val flashcards: List<FlashcardEntity>,
)