package fr.rage.lafie.table.games.points.sheet.domain.usecase

import fr.rage.lafie.table.games.points.sheet.data.model.Player
import fr.rage.lafie.table.games.points.sheet.data.repository.PlayerRepository
import fr.rage.lafie.table.games.points.sheet.domain.mapper.toState
import fr.rage.lafie.table.games.points.sheet.ui.page.player.choose.PlayerState
import fr.rage.lafie.table.games.points.sheet.utils.Result
import fr.rage.lafie.table.games.points.sheet.utils.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single
import java.util.UUID

@Single
class GetMatchPlayerListUseCase(
    private val playerRepository: PlayerRepository,
) {

    operator fun invoke(matchId: UUID): Flow<Result<List<PlayerState>>> =
        playerRepository.getAllByMatchId(matchId).map {
            it.map(List<Player>::toState)
        }
}