package fr.rage.lafie.skull.king.points.sheet.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.rage.lafie.skull.king.points.sheet.data.database.entity.MatchEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface MatchDao {
    // region INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(player: MatchEntity)
    // endregion

    // region SELECT
    @Query("SELECT * FROM `match` WHERE id = :id")
    suspend fun getById(id: UUID): MatchEntity?

    @Query("SELECT * FROM `match` WHERE gameId = :gameId")
    fun getAllByGameId(gameId: UUID): Flow<List<MatchEntity>>
    // endregion
}