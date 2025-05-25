package fr.rage.lafie.skull.king.points.sheet.data.repository.impl

import fr.rage.lafie.skull.king.points.sheet.data.database.dao.MatchDao
import fr.rage.lafie.skull.king.points.sheet.data.database.entity.MatchEntity
import fr.rage.lafie.skull.king.points.sheet.data.exception.EntityNotFoundById
import fr.rage.lafie.skull.king.points.sheet.data.model.Match
import fr.rage.lafie.skull.king.points.sheet.data.repository.MatchRepository
import fr.rage.lafie.skull.king.points.sheet.domain.mapper.toEntity
import fr.rage.lafie.skull.king.points.sheet.domain.mapper.toModel
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    private val dao: MatchDao,
) : MatchRepository {
    // region ADD
    override suspend fun createOrUpdate(match: Match): Result<Match> {
        when (val searchResult = getById(match.id)) {
            is Result.Success -> dao.update(match.toEntity())
            is Result.Error -> if (searchResult.exception is EntityNotFoundById) dao.insert(match.toEntity())
            else -> return searchResult
        }
        return getById(match.id)
    }
    // endregion

    // region GET
    override fun getAll(): Flow<Result<List<Match>>> =
        dao.getAllByGameId().map { matches ->
            Result.Success(matches.toModel())
        }

    override suspend fun getById(id: UUID): Result<Match> = dao.getById(id)?.let {
        Result.Success(it.toModel())
    } ?: Result.Error(EntityNotFoundById(MatchEntity::class, id))
    // endregion
}