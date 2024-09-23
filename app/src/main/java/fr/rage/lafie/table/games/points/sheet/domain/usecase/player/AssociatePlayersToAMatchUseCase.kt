package fr.rage.lafie.table.games.points.sheet.domain.usecase.player

import fr.rage.lafie.table.games.points.sheet.data.model.Player
import fr.rage.lafie.table.games.points.sheet.data.repository.PlayerRepository
import fr.rage.lafie.table.games.points.sheet.utils.Result
import org.koin.core.annotation.Single
import java.util.UUID

@Single
class AssociatePlayersToAMatchUseCase(
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