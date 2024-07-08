package cifor.icraf.flashcardsxml.flashcard.domain.repository

import cifor.icraf.flashcardsxml.flashcard.domain.entity.FlashcardEntity
import cifor.icraf.flashcardsxml.flashcard.domain.entity.SubjectEntity
import cifor.icraf.flashcardsxml.flashcard.domain.relations.SubjectWithFlashcards
import kotlinx.coroutines.flow.Flow

interface FlashcardsXMLRepository {
    suspend fun upsertSubject(subjectEntity: SubjectEntity)

    suspend fun upsertFlashcard(flashcardEntity: FlashcardEntity)

    suspend fun getAllFlashcardsBySubject(): Flow<List<SubjectWithFlashcards>>

    suspend fun searchForSubject(searchQuery: String): Flow<List<SubjectWithFlashcards>>

    suspend fun deleteSubject(subjectEntity: SubjectEntity)

    suspend fun deleteFlashcard(flashcardEntity: FlashcardEntity)

    suspend fun getFlashcardsBySubjectName(subjectName: String): Flow<List<FlashcardEntity>>
}