package fr.rage.lafie.skull.king.points.sheet.data.repository.impl

import fr.rage.lafie.skull.king.points.sheet.data.database.dao.PlayerDao
import fr.rage.lafie.skull.king.points.sheet.data.database.entity.PlayerEntity
import fr.rage.lafie.skull.king.points.sheet.data.exception.EntityNotFoundById
import fr.rage.lafie.skull.king.points.sheet.data.model.Player
import fr.rage.lafie.skull.king.points.sheet.data.repository.PlayerRepository
import fr.rage.lafie.skull.king.points.sheet.domain.mapper.toEntity
import fr.rage.lafie.skull.king.points.sheet.domain.mapper.toModel
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

class PlayerRepositoryImpl @Inject constructor(
    private val dao: PlayerDao,
) : PlayerRepository {

    // region ADD
    override suspend fun associateToMatch(
        players: List<Player>,
        matchId: UUID
    ): Result.Success<Unit> {
        dao.upsert(players.toEntity(matchId))
        return Result.Success(Unit)
    }
    // endregion

    // region GET
    override fun getAllByMatchId(matchId: UUID): Flow<Result<List<Player>>> =
        dao.getAllByMatchId(matchId).map {
            Result.Success(it.toModel())
        }

    override suspend fun getById(id: UUID): Result<Player> = dao.getById(id)?.let {
        Result.Success(it.toModel())
    } ?: Result.Error(EntityNotFoundById(PlayerEntity::class, id))
    // endregion

}