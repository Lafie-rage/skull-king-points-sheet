package fr.rage.lafie.table.games.points.sheet.data.repository.impl

import fr.rage.lafie.table.games.points.sheet.data.database.dao.PlayerDao
import fr.rage.lafie.table.games.points.sheet.data.model.Player
import fr.rage.lafie.table.games.points.sheet.data.repository.PlayerRepository
import fr.rage.lafie.table.games.points.sheet.domain.mapper.toModel
import fr.rage.lafie.table.games.points.sheet.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single
import java.util.UUID

@Single
class PlayerRepositoryImpl(
    private val dao: PlayerDao,
) : PlayerRepository {

    // region GET
    override fun getById(id: UUID): Result<Player> =
        Result.Success(dao.getById(id).toModel())

    override fun getAllByMatchId(matchId: UUID): Flow<Result<List<Player>>> =
        dao.getAllByMatchId(matchId).map {
            Result.Success(it.toModel())
        }
    // endregion

}