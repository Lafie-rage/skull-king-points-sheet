package fr.rage.lafie.skull.king.points.sheet.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.rage.lafie.skull.king.points.sheet.data.database.converter.Converters
import fr.rage.lafie.skull.king.points.sheet.data.database.dao.GameDao
import fr.rage.lafie.skull.king.points.sheet.data.database.dao.MatchDao
import fr.rage.lafie.skull.king.points.sheet.data.database.dao.PlayerDao
import fr.rage.lafie.skull.king.points.sheet.data.database.dao.PlayerPointsDao
import fr.rage.lafie.skull.king.points.sheet.data.database.entity.GameEntity
import fr.rage.lafie.skull.king.points.sheet.data.database.entity.MatchEntity
import fr.rage.lafie.skull.king.points.sheet.data.database.entity.PlayerEntity
import fr.rage.lafie.skull.king.points.sheet.data.database.entity.PlayerPointsEntity

@Database(
    entities = [
        GameEntity::class,
        MatchEntity::class,
        PlayerEntity::class,
        PlayerPointsEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getGameDao(): GameDao

    abstract fun getMatchDao(): MatchDao

    abstract fun getPlayerDao(): PlayerDao

    abstract fun getPlayerPointsDao(): PlayerPointsDao
}