package fr.rage.lafie.skull.king.points.sheet.domain.usecase.shared

import fr.rage.lafie.skull.king.points.sheet.data.model.Game
import fr.rage.lafie.skull.king.points.sheet.data.repository.GameRepository
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import fr.rage.lafie.skull.king.points.sheet.utils.map
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetGameNameByIdUseCase @Inject constructor(
    private val repository: GameRepository,
) {

    suspend operator fun invoke(gameId: UUID): Result<String> = repository.getById(gameId)
        .map(Game::name)
}