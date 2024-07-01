package cifor.icraf.flashcardsxml.dependencyInjection

import androidx.room.Room
import cifor.icraf.flashcardsxml.flashcard.data.local.database.FlashcardsXMLLocalDatabase
import cifor.icraf.flashcardsxml.flashcard.data.repository.FlashcardsXMLRepositoryImplementation
import cifor.icraf.flashcardsxml.flashcard.domain.dao.FlashcardsXMLDao
import cifor.icraf.flashcardsxml.flashcard.domain.repository.FlashcardsXMLRepository
import cifor.icraf.flashcardsxml.flashcard.ui.viewmodel.FlashcardsXMLViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val flashcardsXmlKoinModule =
    module {

        single<CoroutineDispatcher> {
            Dispatchers.IO
        }

        single<FlashcardsXMLRepository> {
            FlashcardsXMLRepositoryImplementation(
                flashcardsXMLDao = get(),
                ioDispatcher = get(),
            )
        }

        viewModel {
            FlashcardsXMLViewModel(
                flashcardsXMLRepository = get(),
            )
        }

        single<FlashcardsXMLLocalDatabase> {
            Room.databaseBuilder(
                context = androidContext(),
                klass = FlashcardsXMLLocalDatabase::class.java,
                name = "FlashcardLocalDatabase",
            ).build()
        }

        single<FlashcardsXMLDao> {
            get<FlashcardsXMLLocalDatabase>().flashcardDao()
        }
    }