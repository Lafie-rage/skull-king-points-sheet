package fr.rage.lafie.skull.king.points.sheet.data.repository.impl

import fr.rage.lafie.skull.king.points.sheet.data.database.dao.PlayerPointsDao
import fr.rage.lafie.skull.king.points.sheet.data.database.entity.PlayerEntity
import fr.rage.lafie.skull.king.points.sheet.data.exception.EntityNotFoundByField
import fr.rage.lafie.skull.king.points.sheet.data.model.PlayerPoints
import fr.rage.lafie.skull.king.points.sheet.data.repository.PlayerPointsRepository
import fr.rage.lafie.skull.king.points.sheet.domain.mapper.toEntity
import fr.rage.lafie.skull.king.points.sheet.domain.mapper.toModel
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import org.koin.core.annotation.Single
import java.util.UUID

@Single
class PlayerPointsRepositoryImpl(
    private val dao: PlayerPointsDao,
) : PlayerPointsRepository {

    // region ADD
    override suspend fun save(playerPoints: PlayerPoints): Result<Unit> {
        dao.upsert(playerPoints.toEntity())
        return Result.Success(Unit)
    }
    // endregion

    // region GET
    override suspend fun getByPlayerId(playerId: UUID): Result<List<PlayerPoints>> =
        dao.getByPlayerId(playerId).let {
            Result.Success(it.toModel())
        }

    override suspend fun getByPlayerIdAndRoundIndex(
        playerId: UUID,
        roundIndex: Int
    ): Result<PlayerPoints> =
        dao.getByPlayerIdAndRoundIndex(playerId, roundIndex)?.let {
            Result.Success(it.toModel())
        } ?: Result.Error(
            EntityNotFoundByField(
                entityName = PlayerEntity::class,
                searchField = "player id",
                value = playerId,
            ),
        )
    // endregion

}