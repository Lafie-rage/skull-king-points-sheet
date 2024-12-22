package fr.rage.lafie.skull.king.points.sheet.domain.usecase.game

import fr.rage.lafie.skull.king.points.sheet.data.model.Game
import fr.rage.lafie.skull.king.points.sheet.data.repository.GameRepository
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetGameByIdUseCase @Inject constructor(
    private val repository: GameRepository,
) {
    suspend operator fun invoke(id: UUID): Result<Game> {
        return repository.getById(id)
    }
}