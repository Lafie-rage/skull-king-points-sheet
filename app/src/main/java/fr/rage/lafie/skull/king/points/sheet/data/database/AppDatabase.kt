package fr.rage.lafie.skull.king.points.sheet.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.DeleteTable
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import fr.rage.lafie.skull.king.points.sheet.data.database.converter.Converters
import fr.rage.lafie.skull.king.points.sheet.data.database.dao.MatchDao
import fr.rage.lafie.skull.king.points.sheet.data.database.dao.PlayerDao
import fr.rage.lafie.skull.king.points.sheet.data.database.dao.PlayerPointsDao
import fr.rage.lafie.skull.king.points.sheet.data.database.entity.MatchEntity
import fr.rage.lafie.skull.king.points.sheet.data.database.entity.PlayerEntity
import fr.rage.lafie.skull.king.points.sheet.data.database.entity.PlayerPointsEntity

@Database(
    entities = [
        MatchEntity::class,
        PlayerEntity::class,
        PlayerPointsEntity::class,
    ],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2, spec = AppDatabase.RemoveGameTable::class)
    ]
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getMatchDao(): MatchDao

    abstract fun getPlayerDao(): PlayerDao

    abstract fun getPlayerPointsDao(): PlayerPointsDao

    @DeleteColumn(tableName = "match", columnName = "gameId")
    @DeleteTable(tableName = "game")
    class RemoveGameTable : AutoMigrationSpec

}