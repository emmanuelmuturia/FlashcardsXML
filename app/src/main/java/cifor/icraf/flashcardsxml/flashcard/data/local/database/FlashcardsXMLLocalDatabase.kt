package cifor.icraf.flashcardsxml.flashcard.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cifor.icraf.flashcardsxml.flashcard.domain.dao.FlashcardsXMLDao
import cifor.icraf.flashcardsxml.flashcard.domain.entity.FlashcardEntity
import cifor.icraf.flashcardsxml.flashcard.domain.entity.SubjectEntity

@Database(entities = [SubjectEntity::class, FlashcardEntity::class], version = 1, exportSchema = false)
abstract class FlashcardsXMLLocalDatabase : RoomDatabase() {
    abstract fun flashcardDao(): FlashcardsXMLDao
}