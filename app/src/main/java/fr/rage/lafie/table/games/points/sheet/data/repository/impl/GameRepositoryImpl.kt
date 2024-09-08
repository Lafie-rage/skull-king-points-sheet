package fr.rage.lafie.table.games.points.sheet.data.repository.impl

import fr.rage.lafie.table.games.points.sheet.data.database.dao.GameDao
import fr.rage.lafie.table.games.points.sheet.data.database.entity.GameEntity
import fr.rage.lafie.table.games.points.sheet.data.exception.EntityNotFoundById
import fr.rage.lafie.table.games.points.sheet.data.model.Game
import fr.rage.lafie.table.games.points.sheet.data.repository.GameRepository
import fr.rage.lafie.table.games.points.sheet.domain.mapper.toEntity
import fr.rage.lafie.table.games.points.sheet.domain.mapper.toModel
import fr.rage.lafie.table.games.points.sheet.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single
import java.util.UUID

@Single
class GameRepositoryImpl(
    private val dao: GameDao,
) : GameRepository {
    // region ADD
    override suspend fun create(game: Game): Result<Game> {
        dao.upsert(game.toEntity())
        return getById(game.id)
    }
    // endregion

    // region GET
    override fun getAll(): Flow<Result<List<Game>>> = dao.getAll()
        .map { Result.Success(it.toModel()) }

    override suspend fun getById(id: UUID): Result<Game> = dao.getById(id)?.let {
        Result.Success(it.toModel())
    } ?: Result.Error(EntityNotFoundById(GameEntity::class, id))
    // endregion
}