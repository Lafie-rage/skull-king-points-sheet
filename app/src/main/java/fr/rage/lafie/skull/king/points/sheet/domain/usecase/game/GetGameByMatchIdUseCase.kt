package fr.rage.lafie.skull.king.points.sheet.domain.usecase.game

import fr.rage.lafie.skull.king.points.sheet.data.model.Game
import fr.rage.lafie.skull.king.points.sheet.data.repository.GameRepository
import fr.rage.lafie.skull.king.points.sheet.data.repository.MatchRepository
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import fr.rage.lafie.skull.king.points.sheet.utils.flatMap
import org.koin.core.annotation.Single
import java.util.UUID

@Single
class GetGameByMatchIdUseCase(
    private val gameRepository: GameRepository,
    private val matchRepository: MatchRepository,
) {
    suspend operator fun invoke(
        matchId: UUID
    ): Result<Game> {
        return matchRepository.getById(matchId)
            .flatMap { match ->
                gameRepository.getById(match.gameId)
            }
    }
}