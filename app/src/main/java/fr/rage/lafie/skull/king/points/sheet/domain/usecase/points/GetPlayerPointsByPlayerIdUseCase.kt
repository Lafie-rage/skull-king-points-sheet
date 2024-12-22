package fr.rage.lafie.skull.king.points.sheet.domain.usecase.points

import fr.rage.lafie.skull.king.points.sheet.data.model.PlayerPoints
import fr.rage.lafie.skull.king.points.sheet.data.repository.PlayerPointsRepository
import fr.rage.lafie.skull.king.points.sheet.domain.mapper.toState
import fr.rage.lafie.skull.king.points.sheet.ui.page.player.point.state.PlayerPointsState
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import fr.rage.lafie.skull.king.points.sheet.utils.map
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPlayerPointsByPlayerIdUseCase @Inject constructor(
    private val repository: PlayerPointsRepository,
) {
    suspend operator fun invoke(playerId: UUID): Result<List<PlayerPointsState>> =
        repository.getByPlayerId(playerId).map { result -> result.map(PlayerPoints::toState) }
}