package fr.rage.lafie.table.games.points.sheet.di

import android.app.Application
import androidx.room.Room
import fr.rage.lafie.table.games.points.sheet.data.database.AppDatabase
import fr.rage.lafie.table.games.points.sheet.data.database.dao.GameDao
import fr.rage.lafie.table.games.points.sheet.data.database.dao.MatchDao
import fr.rage.lafie.table.games.points.sheet.data.database.dao.PlayerDao
import fr.rage.lafie.table.games.points.sheet.data.database.dao.PlayerPointsDao
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class DatabaseModule {

    @Single
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "database.db"
        ).build()
    }

    @Single
    fun provideGameDao(database: AppDatabase): GameDao {
        return database.getGameDao()
    }

    @Single
    fun provideMatchDao(database: AppDatabase): MatchDao {
        return database.getMatchDao()
    }

    @Single
    fun providePlayerDao(database: AppDatabase): PlayerDao {
        return database.getPlayerDao()
    }

    @Single
    fun providePlayerPointsDao(database: AppDatabase): PlayerPointsDao {
        return database.getPlayerPointsDao()
    }
}