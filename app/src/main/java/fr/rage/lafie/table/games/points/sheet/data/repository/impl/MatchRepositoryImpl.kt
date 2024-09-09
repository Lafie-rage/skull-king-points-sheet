package fr.rage.lafie.table.games.points.sheet.data.repository.impl

import fr.rage.lafie.table.games.points.sheet.data.database.dao.MatchDao
import fr.rage.lafie.table.games.points.sheet.data.database.entity.MatchEntity
import fr.rage.lafie.table.games.points.sheet.data.exception.EntityNotFoundById
import fr.rage.lafie.table.games.points.sheet.data.model.Match
import fr.rage.lafie.table.games.points.sheet.data.repository.MatchRepository
import fr.rage.lafie.table.games.points.sheet.domain.mapper.toEntity
import fr.rage.lafie.table.games.points.sheet.domain.mapper.toModel
import fr.rage.lafie.table.games.points.sheet.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single
import java.util.UUID

@Single
class MatchRepositoryImpl(
    private val dao: MatchDao,
) : MatchRepository {
    // region ADD
    override suspend fun create(match: Match): Result<Match> {
        dao.upsert(match.toEntity())
        return getById(match.id)
    }
    // endregion

    // region GET
    override fun getAllByGameId(gameId: UUID): Flow<Result<List<Match>>> =
        dao.getAllByGameId(gameId)
            .map { matches ->
                Result.Success(matches.toModel())
            }

    override suspend fun getById(id: UUID): Result<Match> = dao.getById(id)?.let {
        Result.Success(it.toModel())
    } ?: Result.Error(EntityNotFoundById(MatchEntity::class, id))
    // endregion
}