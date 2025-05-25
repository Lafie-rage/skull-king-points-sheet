package fr.rage.lafie.skull.king.points.sheet.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.rage.lafie.skull.king.points.sheet.data.database.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface PlayerDao {

    // region INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(players: List<PlayerEntity>)
    // endregion

    // region SELECT
    @Query("SELECT * FROM player WHERE id = :id")
    suspend fun getById(id: UUID): PlayerEntity?

    @Query("SELECT * FROM player WHERE matchId = :matchId")
    fun getAllByMatchId(matchId: UUID): Flow<List<PlayerEntity>>
    // endregion

}