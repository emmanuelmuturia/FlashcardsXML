package cifor.icraf.flashcardsxml.flashcard.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import cifor.icraf.flashcardsxml.flashcard.domain.entity.FlashcardEntity
import cifor.icraf.flashcardsxml.flashcard.domain.entity.SubjectEntity
import cifor.icraf.flashcardsxml.flashcard.domain.relations.SubjectWithFlashcards
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashcardsXMLDao {
    @Upsert
    suspend fun upsertSubject(subjectEntity: SubjectEntity)

    @Upsert
    suspend fun upsertFlashcard(flashcardEntity: FlashcardEntity)

    @Transaction
    @Query(value = "SELECT * FROM SubjectEntity")
    fun getAllSubjectsWithFlashcards(): Flow<List<SubjectWithFlashcards>>

    @Delete
    suspend fun deleteSubject(subjectEntity: SubjectEntity)

    @Delete
    suspend fun deleteFlashcard(flashcardEntity: FlashcardEntity)

    @Query(value = "DELETE FROM FlashcardEntity WHERE flashcardSubjectName = :subjectName")
    suspend fun deleteFlashcardsBySubject(subjectName: String)

    @Query(value = "SELECT * FROM FlashcardEntity WHERE flashcardSubjectName = :subjectName")
    suspend fun getFlashcardsBySubjectName(subjectName: String): Flow<List<FlashcardEntity>>
}