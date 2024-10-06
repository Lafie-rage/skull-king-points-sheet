package fr.rage.lafie.skull.king.points.sheet.domain.usecase.game

import fr.rage.lafie.skull.king.points.sheet.data.model.Game
import fr.rage.lafie.skull.king.points.sheet.data.repository.GameRepository
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import org.koin.core.annotation.Single
import java.util.UUID

@Single
class GetGameByIdUseCase(
    private val repository: GameRepository,
) {
    suspend operator fun invoke(id: UUID): Result<Game> {
        return repository.getById(id)
    }
}