package cifor.icraf.flashcardsxml.main.application

import android.app.Application
import cifor.icraf.flashcardsxml.dependencyInjection.flashcardsXmlKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FlashCardsXMLApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(androidContext = applicationContext)
            modules(modules = flashcardsXmlKoinModule)
        }
    }
}