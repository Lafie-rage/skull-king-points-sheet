package fr.rage.lafie.table.games.points.sheet.domain.usecase.round

import fr.rage.lafie.table.games.points.sheet.data.model.Match
import fr.rage.lafie.table.games.points.sheet.data.repository.MatchRepository
import fr.rage.lafie.table.games.points.sheet.ui.page.round.state.RoundState
import fr.rage.lafie.table.games.points.sheet.utils.Result
import fr.rage.lafie.table.games.points.sheet.utils.map
import java.util.UUID

class GetRoundsByMatchIdUseCase(
    private val repository: MatchRepository,
) {

    suspend operator fun invoke(matchId: UUID): Result<List<RoundState>> {
        return repository.getById(matchId)
            .map(Match::roundCounter)
            .map { roundCounter ->
                (0 until roundCounter).map {
                    RoundState(roundCounter)
                }
            }
    }
}