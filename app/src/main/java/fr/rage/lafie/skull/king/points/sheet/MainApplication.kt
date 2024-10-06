package fr.rage.lafie.skull.king.points.sheet

import android.app.Application
import fr.rage.lafie.skull.king.points.sheet.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(AppModule().module)
        }
    }
}