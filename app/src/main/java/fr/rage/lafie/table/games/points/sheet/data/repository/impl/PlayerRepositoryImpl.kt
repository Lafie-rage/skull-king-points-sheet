package fr.rage.lafie.table.games.points.sheet.data.repository.impl

import fr.rage.lafie.table.games.points.sheet.data.database.dao.PlayerDao
import fr.rage.lafie.table.games.points.sheet.data.model.Player
import fr.rage.lafie.table.games.points.sheet.data.repository.PlayerRepository
import fr.rage.lafie.table.games.points.sheet.domain.mapper.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single
import java.util.UUID

@Single
class PlayerRepositoryImpl(
    private val dao: PlayerDao,
) : PlayerRepository {

    override fun getAllByMatchId(matchId: UUID): Flow<List<Player>> =
        dao.getAllByMatchId(matchId).map {
            it.toModel()
        }

}