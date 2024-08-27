package fr.rage.lafie.table.games.points.sheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import fr.rage.lafie.table.games.points.sheet.di.AppModule
import fr.rage.lafie.table.games.points.sheet.ui.routing.Router
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class MainActivity : ComponentActivity() {
    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(AppModule().module)
        }

        enableEdgeToEdge()
        setContent {
            KoinAndroidContext {
                Router()
            }
        }
    }
}
