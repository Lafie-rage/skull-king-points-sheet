package fr.rage.lafie.skull.king.points.sheet.domain.usecase.player

import fr.rage.lafie.skull.king.points.sheet.data.model.Player
import fr.rage.lafie.skull.king.points.sheet.data.repository.PlayerRepository
import fr.rage.lafie.skull.king.points.sheet.domain.mapper.toState
import fr.rage.lafie.skull.king.points.sheet.ui.page.player.choose.state.PlayerState
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import fr.rage.lafie.skull.king.points.sheet.utils.map
import org.koin.core.annotation.Single
import java.util.UUID

@Single
class GetPlayerByIdUseCase(
    private val playerRepository: PlayerRepository,
) {

    suspend operator fun invoke(playerId: UUID): Result<PlayerState> =
        playerRepository.getById(playerId).map(Player::toState)
}