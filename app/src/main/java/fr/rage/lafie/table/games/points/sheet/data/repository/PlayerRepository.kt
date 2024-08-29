package fr.rage.lafie.table.games.points.sheet.data.repository

import fr.rage.lafie.table.games.points.sheet.data.model.Player
import fr.rage.lafie.table.games.points.sheet.utils.Result
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface PlayerRepository {

    // region GET
    fun getAllByMatchId(matchId: UUID): Flow<Result<List<Player>>>

    fun getById(id: UUID): Result<Player>
    // endregion
}