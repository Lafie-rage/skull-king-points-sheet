package fr.rage.lafie.table.games.points.sheet.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import fr.rage.lafie.table.games.points.sheet.data.database.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface PlayerDao {

    @Query("SELECT * FROM player WHERE matchId = :matchId")
    fun getAllByMatchId(matchId: UUID): Flow<List<PlayerEntity>>
}