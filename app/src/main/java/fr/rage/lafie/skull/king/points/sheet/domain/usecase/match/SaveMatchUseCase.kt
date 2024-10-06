package fr.rage.lafie.skull.king.points.sheet.domain.usecase.match

import fr.rage.lafie.skull.king.points.sheet.data.model.Match
import fr.rage.lafie.skull.king.points.sheet.data.repository.MatchRepository
import fr.rage.lafie.skull.king.points.sheet.domain.usecase.player.AssociatePlayersToAMatchUseCase
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import fr.rage.lafie.skull.king.points.sheet.utils.flatMap
import org.koin.core.annotation.Single
import java.util.UUID

@Single
class SaveMatchUseCase(
    private val matchRepository: MatchRepository,
    private val savePlayers: AssociatePlayersToAMatchUseCase,
) {
    suspend operator fun invoke(
        id: UUID,
        gameId: UUID,
        name: String,
        playersName: List<String>,
    ): Result<Unit> {
        return matchRepository.createOrUpdate(
            Match(
                id = id,
                gameId = gameId,
                name = name,
                roundCounter = 1,
                isFinished = false,
            )
        ).flatMap { match ->
            savePlayers(playersName, match.id)
        }
    }
}