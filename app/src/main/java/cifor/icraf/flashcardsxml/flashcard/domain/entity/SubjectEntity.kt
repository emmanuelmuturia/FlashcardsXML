package cifor.icraf.flashcardsxml.flashcard.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "SubjectEntity")
@Serializable
data class SubjectEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "subjectName")
    val subjectName: String,
    @ColumnInfo(name = "subjectDescription")
    val subjectDescription: String? = null,
)