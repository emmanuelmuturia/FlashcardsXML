package cifor.icraf.flashcardsxml.flashcard.data.repository

import cifor.icraf.flashcardsxml.flashcard.domain.dao.FlashcardsXMLDao
import cifor.icraf.flashcardsxml.flashcard.domain.entity.FlashcardEntity
import cifor.icraf.flashcardsxml.flashcard.domain.entity.SubjectEntity
import cifor.icraf.flashcardsxml.flashcard.domain.relations.SubjectWithFlashcards
import cifor.icraf.flashcardsxml.flashcard.domain.repository.FlashcardsXMLRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class FlashcardsXMLRepositoryImplementation(
    private val ioDispatcher: CoroutineDispatcher,
    private val flashcardsXMLDao: FlashcardsXMLDao,
) : FlashcardsXMLRepository {
    override suspend fun upsertSubject(subjectEntity: SubjectEntity) {
        withContext(context = ioDispatcher) {
            flashcardsXMLDao.upsertSubject(subjectEntity = subjectEntity)
        }
    }

    override suspend fun upsertFlashcard(flashcardEntity: FlashcardEntity) {
        withContext(context = ioDispatcher) {
            flashcardsXMLDao.upsertFlashcard(flashcardEntity = flashcardEntity)
        }
    }

    override suspend fun getAllSubjectsWithFlashcards(): Flow<List<SubjectWithFlashcards>> {
        return withContext(context = ioDispatcher) {
            flashcardsXMLDao.getAllSubjectsWithFlashcards()
        }
    }

    override suspend fun searchForSubject(searchQuery: String): Flow<List<SubjectWithFlashcards>> {
        return withContext(context = ioDispatcher) {
            flashcardsXMLDao.getAllSubjectsWithFlashcards().map {
                it.filter { subjectWithFlashcards ->
                    subjectWithFlashcards.subjectEntity.subjectName.contains(
                        other = searchQuery,
                        ignoreCase = true,
                    )
                }
            }
        }
    }

    override suspend fun deleteSubject(subjectEntity: SubjectEntity) {
        withContext(context = ioDispatcher) {
            flashcardsXMLDao.deleteSubject(subjectEntity = subjectEntity)
            flashcardsXMLDao.deleteFlashcardsBySubject(subjectName = subjectEntity.subjectName)
        }
    }

    override suspend fun deleteFlashcard(flashcardEntity: FlashcardEntity) {
        withContext(context = ioDispatcher) {
            flashcardsXMLDao.deleteFlashcard(flashcardEntity = flashcardEntity)
        }
    }

    override suspend fun getAllFlashcards(): Flow<List<FlashcardEntity>> {
        return withContext(context = ioDispatcher) {
            flashcardsXMLDao.getAllFlashcards()
        }
    }
}