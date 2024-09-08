package fr.rage.lafie.table.games.points.sheet.domain.usecase.points

import fr.rage.lafie.table.games.points.sheet.data.model.PlayerPoints
import fr.rage.lafie.table.games.points.sheet.data.repository.PlayerPointsRepository
import fr.rage.lafie.table.games.points.sheet.domain.mapper.toState
import fr.rage.lafie.table.games.points.sheet.ui.page.player.round.point.PlayerPointsState
import fr.rage.lafie.table.games.points.sheet.utils.Result
import fr.rage.lafie.table.games.points.sheet.utils.map
import org.koin.core.annotation.Single
import java.util.UUID

@Single
class GetPlayerPointsByPlayerIdUseCase(
    private val repository: PlayerPointsRepository,
) {
    suspend operator fun invoke(playerId: UUID): Result<PlayerPointsState> =
        repository.getByPlayerId(playerId).map(PlayerPoints::toState)
}