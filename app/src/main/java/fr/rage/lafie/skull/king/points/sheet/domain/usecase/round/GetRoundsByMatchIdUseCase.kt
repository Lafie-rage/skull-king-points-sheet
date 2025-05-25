package fr.rage.lafie.skull.king.points.sheet.domain.usecase.round

import fr.rage.lafie.skull.king.points.sheet.data.model.Match
import fr.rage.lafie.skull.king.points.sheet.data.repository.MatchRepository
import fr.rage.lafie.skull.king.points.sheet.ui.page.round.state.RoundState
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import fr.rage.lafie.skull.king.points.sheet.utils.map
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetRoundsByMatchIdUseCase @Inject constructor(
    private val repository: MatchRepository,
) {

    suspend operator fun invoke(matchId: UUID): Result<List<RoundState>> {
        return repository.getById(matchId)
            .map(Match::roundCounter)
            .map { roundCounter ->
                (1..roundCounter).map { index ->
                    RoundState(index)
                }
            }
    }
}