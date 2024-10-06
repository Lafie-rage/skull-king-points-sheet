package fr.rage.lafie.skull.king.points.sheet.domain.usecase.points

import fr.rage.lafie.skull.king.points.sheet.data.repository.PlayerPointsRepository
import fr.rage.lafie.skull.king.points.sheet.domain.mapper.toModel
import fr.rage.lafie.skull.king.points.sheet.ui.page.player.point.state.PlayerPointsState
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import org.koin.core.annotation.Single

@Single
class SavePlayerPointsUseCase(
    private val repository: PlayerPointsRepository,
) {

    suspend operator fun invoke(playerPoints: PlayerPointsState): Result<Unit> =
        repository.save(playerPoints.toModel())

}