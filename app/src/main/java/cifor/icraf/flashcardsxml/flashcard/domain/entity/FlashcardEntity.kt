package cifor.icraf.flashcardsxml.flashcard.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "FlashcardEntity")
@Serializable
data class FlashcardEntity(
    @ColumnInfo(name = "flashcardSubjectName")
    val flashcardSubjectName: String,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "flashCardTerm")
    val flashCardTerm: String,
    @ColumnInfo(name = "flashCardDefinition")
    val flashCardDefinition: String,
)