package fr.rage.lafie.skull.king.points.sheet.data.repository

import fr.rage.lafie.skull.king.points.sheet.data.model.PlayerPoints
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import java.util.UUID

interface PlayerPointsRepository {

    // region ADD
    suspend fun save(playerPoints: PlayerPoints): Result<Unit>
    // endregion

    // region GET
    suspend fun getByPlayerId(playerId: UUID): Result<List<PlayerPoints>>

    suspend fun getByPlayerIdAndRoundIndex(playerId: UUID, roundIndex: Int): Result<PlayerPoints>
    // endregion
}