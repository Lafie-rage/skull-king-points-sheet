package fr.rage.lafie.table.games.points.sheet.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.rage.lafie.table.games.points.sheet.data.database.entity.GameEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface GameDao {
    // region INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(player: GameEntity)
    // endregion

    // region SELECT
    @Query("SELECT * FROM game WHERE id = :id")
    suspend fun getById(id: UUID): GameEntity?

    @Query("SELECT * FROM game")
    fun getAll(): Flow<List<GameEntity>>
    // endregion
}