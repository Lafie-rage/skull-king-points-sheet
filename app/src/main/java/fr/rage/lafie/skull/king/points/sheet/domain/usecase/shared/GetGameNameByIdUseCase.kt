package fr.rage.lafie.skull.king.points.sheet.domain.usecase.shared

import fr.rage.lafie.skull.king.points.sheet.data.model.Game
import fr.rage.lafie.skull.king.points.sheet.data.repository.GameRepository
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import fr.rage.lafie.skull.king.points.sheet.utils.map
import org.koin.core.annotation.Single
import java.util.UUID

@Single
class GetGameNameByIdUseCase(
    private val repository: GameRepository,
) {

    suspend operator fun invoke(gameId: UUID): Result<String> = repository.getById(gameId)
        .map(Game::name)
}