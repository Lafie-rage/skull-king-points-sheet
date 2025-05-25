package fr.rage.lafie.skull.king.points.sheet.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.rage.lafie.skull.king.points.sheet.data.database.entity.PlayerPointsEntity
import java.util.UUID

@Dao
interface PlayerPointsDao {

    // Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(playerPointsEntity: PlayerPointsEntity)
    // endregion

    // region SELECT
    @Query("SELECT * FROM player_points WHERE playerId = :playerId")
    suspend fun getByPlayerId(playerId: UUID): List<PlayerPointsEntity>

    @Query("SELECT * FROM player_points WHERE playerId = :playerId AND roundIndex = :roundIndex")
    suspend fun getByPlayerIdAndRoundIndex(playerId: UUID, roundIndex: Int): PlayerPointsEntity?
    // endregion

}