package fr.rage.lafie.skull.king.points.sheet.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.rage.lafie.skull.king.points.sheet.data.database.AppDatabase
import fr.rage.lafie.skull.king.points.sheet.data.database.dao.GameDao
import fr.rage.lafie.skull.king.points.sheet.data.database.dao.MatchDao
import fr.rage.lafie.skull.king.points.sheet.data.database.dao.PlayerDao
import fr.rage.lafie.skull.king.points.sheet.data.database.dao.PlayerPointsDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "database.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideGameDao(database: AppDatabase): GameDao {
        return database.getGameDao()
    }

    @Singleton
    @Provides
    fun provideMatchDao(database: AppDatabase): MatchDao {
        return database.getMatchDao()
    }

    @Singleton
    @Provides
    fun providePlayerDao(database: AppDatabase): PlayerDao {
        return database.getPlayerDao()
    }

    @Singleton
    @Provides
    fun providePlayerPointsDao(database: AppDatabase): PlayerPointsDao {
        return database.getPlayerPointsDao()
    }
}