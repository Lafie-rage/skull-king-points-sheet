package fr.rage.lafie.table.games.points.sheet.data.repository

import fr.rage.lafie.table.games.points.sheet.data.model.Player
import fr.rage.lafie.table.games.points.sheet.utils.Result
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface PlayerRepository {
    // region ADD
    suspend fun associateToMatch(players: List<Player>, matchId: UUID)
    // endregion

    // region GET
    fun getAllByMatchId(matchId: UUID): Flow<Result<List<Player>>>

    suspend fun getById(id: UUID): Result<Player>
    // endregion
}