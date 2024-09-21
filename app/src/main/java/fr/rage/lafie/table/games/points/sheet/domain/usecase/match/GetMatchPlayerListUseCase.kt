package fr.rage.lafie.table.games.points.sheet.domain.usecase.match

import fr.rage.lafie.table.games.points.sheet.data.model.Player
import fr.rage.lafie.table.games.points.sheet.data.repository.GameRepository
import fr.rage.lafie.table.games.points.sheet.data.repository.MatchRepository
import fr.rage.lafie.table.games.points.sheet.data.repository.PlayerRepository
import fr.rage.lafie.table.games.points.sheet.utils.Result
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single
import java.util.UUID

@Single
class GetMatchPlayerListUseCase(
    private val playerRepository: PlayerRepository,
    private val matchRepository: MatchRepository,
    private val gameRepository: GameRepository,
) {

    operator fun invoke(matchId: UUID): Flow<Result<List<Player>>> =
        playerRepository.getAllByMatchId(matchId)
}