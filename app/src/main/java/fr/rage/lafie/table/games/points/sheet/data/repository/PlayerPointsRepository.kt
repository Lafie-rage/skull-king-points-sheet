package fr.rage.lafie.table.games.points.sheet.data.repository

import fr.rage.lafie.table.games.points.sheet.data.model.PlayerPoints
import fr.rage.lafie.table.games.points.sheet.utils.Result
import java.util.UUID

interface PlayerPointsRepository {

    // region ADD
    suspend fun save(playerPoints: PlayerPoints): Result<Unit>
    // endregion

    // region GET
    suspend fun getByPlayerId(playerId: UUID): Result<PlayerPoints>
    // endregion
}