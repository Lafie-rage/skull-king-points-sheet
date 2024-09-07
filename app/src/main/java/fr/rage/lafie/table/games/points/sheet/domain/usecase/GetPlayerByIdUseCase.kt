package fr.rage.lafie.table.games.points.sheet.domain.usecase

import fr.rage.lafie.table.games.points.sheet.data.model.Player
import fr.rage.lafie.table.games.points.sheet.data.repository.PlayerRepository
import fr.rage.lafie.table.games.points.sheet.domain.mapper.toState
import fr.rage.lafie.table.games.points.sheet.ui.page.player.choose.PlayerState
import fr.rage.lafie.table.games.points.sheet.utils.Result
import fr.rage.lafie.table.games.points.sheet.utils.map
import org.koin.core.annotation.Single
import java.util.UUID

@Single
class GetPlayerByIdUseCase(
    private val playerRepository: PlayerRepository,
) {

    suspend operator fun invoke(playerId: UUID): Result<PlayerState> =
        playerRepository.getById(playerId).map(Player::toState)
}