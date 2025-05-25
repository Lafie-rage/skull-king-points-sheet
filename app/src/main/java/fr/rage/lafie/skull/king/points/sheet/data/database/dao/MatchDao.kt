package fr.rage.lafie.skull.king.points.sheet.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.rage.lafie.skull.king.points.sheet.data.database.entity.MatchEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface MatchDao {
    // region INSERT
    @Insert
    suspend fun insert(player: MatchEntity)
    // endregion

    // region UPDATE
    @Update
    suspend fun update(player: MatchEntity)
    // endregion

    // region SELECT
    @Query("SELECT * FROM `match` WHERE id = :id")
    suspend fun getById(id: UUID): MatchEntity?

    @Query("SELECT * FROM `match`")
    fun getAllByGameId(): Flow<List<MatchEntity>>
    // endregion
}