package cifor.icraf.flashcardsxml

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import cifor.icraf.flashcardsxml.flashcard.data.local.database.FlashcardsXMLLocalDatabase
import cifor.icraf.flashcardsxml.flashcard.domain.dao.FlashcardsXMLDao
import cifor.icraf.flashcardsxml.flashcard.domain.entity.FlashcardEntity
import cifor.icraf.flashcardsxml.flashcard.domain.entity.SubjectEntity
import cifor.icraf.flashcardsxml.flashcard.domain.relations.SubjectWithFlashcards
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(value = AndroidJUnit4::class)
class FlashcardsDaoTest {
    private lateinit var flashcardsLocalDatabase: FlashcardsXMLLocalDatabase
    private lateinit var flashcardDao: FlashcardsXMLDao

    @Before
    fun createLocalDatabase() {
        flashcardsLocalDatabase = Room.inMemoryDatabaseBuilder(
            context = ApplicationProvider.getApplicationContext(),
            klass = FlashcardsXMLLocalDatabase::class.java
        ).allowMainThreadQueries().build()
        flashcardDao = flashcardsLocalDatabase.flashcardDao()
    }

    @Test
    fun testUpsertSubject() = runTest {
        val subjectEntity = SubjectEntity(
            subjectName = "Android"
        )
        flashcardDao.upsertSubject(subjectEntity = subjectEntity)
        val subjects = flashcardDao.getAllSubjectsWithFlashcards()
        assert(value = subjects.first().contains(
            element = SubjectWithFlashcards(
                subjectEntity = subjectEntity,
                flashcards = emptyList()
            )
        ))
    }

    @Test
    fun testUpsertFlashcard() = runTest {
        val subjectEntity = SubjectEntity(
            subjectName = "Android"
        )
        flashcardDao.upsertSubject(subjectEntity = subjectEntity)
        val flashcardEntity = FlashcardEntity(
            flashcardSubjectName = subjectEntity.subjectName,
            flashCardTerm = "Activity",
            flashCardDefinition = "A component in Android..."
        )
        flashcardDao.upsertFlashcard(flashcardEntity = flashcardEntity)
        val subjectWithFlashcard = flashcardDao.getAllSubjectsWithFlashcards()
        assert(value = subjectWithFlashcard.first().map { it.flashcards }.contains(element = listOf(flashcardEntity)))
    }

    @Test
    fun testEachSubjectHasUniqueFlashcards() = runTest {
        val firstSubject = SubjectEntity(
            subjectName = "Android"
        )
        val secondSubject = SubjectEntity(
            subjectName = "Kotlin"
        )
        flashcardDao.upsertSubject(subjectEntity = firstSubject)
        flashcardDao.upsertSubject(subjectEntity = secondSubject)
        val firstFlashcard = FlashcardEntity(
            flashcardSubjectName = firstSubject.subjectName,
            flashCardTerm = "Activity",
            flashCardDefinition = "A component in Android..."
        )
        val secondFlashcard = FlashcardEntity(
            flashcardSubjectName = secondSubject.subjectName,
            flashCardTerm = "Coroutine",
            flashCardDefinition = "The best thing about Kotlin..."
        )
        flashcardDao.upsertFlashcard(flashcardEntity = firstFlashcard)
        flashcardDao.upsertFlashcard(flashcardEntity = secondFlashcard)
        val subjectsWithFlashcard = flashcardDao.getAllSubjectsWithFlashcards()
        assert(value = subjectsWithFlashcard.first()[0].flashcards != subjectsWithFlashcard.first()[1].flashcards)
    }

    @Test
    fun testDeleteFlashcardWhenSubjectDeleted() = runTest {
        val subjectEntity = SubjectEntity(
            subjectName = "Android"
        )
        flashcardDao.upsertSubject(subjectEntity = subjectEntity)
        val flashcardEntity = FlashcardEntity(
            flashcardSubjectName = subjectEntity.subjectName,
            flashCardTerm = "Activity",
            flashCardDefinition = "A component in Android..."
        )
        flashcardDao.upsertFlashcard(flashcardEntity = flashcardEntity)
        flashcardDao.deleteSubject(subjectEntity = subjectEntity)
        val subjectsWithFlashcard = flashcardDao.getAllSubjectsWithFlashcards()
        assert(value = subjectsWithFlashcard.first().map { it.flashcards }.isEmpty())
    }

    @After
    fun closeLocalDatabase() {
        flashcardsLocalDatabase.close()
    }
}