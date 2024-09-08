package fr.rage.lafie.table.games.points.sheet.data.repository

import fr.rage.lafie.table.games.points.sheet.data.model.Game
import fr.rage.lafie.table.games.points.sheet.utils.Result
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface GameRepository {
    // region ADD
    suspend fun create(game: Game): Result<Game>
    // endregion

    // region GET
    fun getAll(): Flow<Result<List<Game>>>

    suspend fun getById(id: UUID): Result<Game>
    // endregion
}