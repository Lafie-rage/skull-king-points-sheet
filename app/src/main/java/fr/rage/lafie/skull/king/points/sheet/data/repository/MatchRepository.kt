package fr.rage.lafie.skull.king.points.sheet.data.repository

import fr.rage.lafie.skull.king.points.sheet.data.model.Match
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface MatchRepository {
    // region ADD
    suspend fun createOrUpdate(match: Match): Result<Match>
    // endregion

    // region GET
    fun getAllByGameId(gameId: UUID): Flow<Result<List<Match>>>

    suspend fun getById(id: UUID): Result<Match>
    // endregion
}