package fr.rage.lafie.skull.king.points.sheet.domain.usecase.player

import fr.rage.lafie.skull.king.points.sheet.data.model.Player
import fr.rage.lafie.skull.king.points.sheet.data.repository.PlayerRepository
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AssociatePlayersToAMatchUseCase @Inject constructor(
    private val repository: PlayerRepository,
) {
    suspend operator fun invoke(
        playersName: List<String>,
        matchId: UUID
    ): Result<Unit> {
        return repository.associateToMatch(
            players = playersName.map { name ->
                Player(
                    id = UUID.randomUUID(),
                    name = name,
                )
            },
            matchId = matchId,
        )
    }
}