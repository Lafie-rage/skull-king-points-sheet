package fr.rage.lafie.skull.king.points.sheet.domain.usecase.match

import fr.rage.lafie.skull.king.points.sheet.data.model.Match
import fr.rage.lafie.skull.king.points.sheet.data.repository.MatchRepository
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import fr.rage.lafie.skull.king.points.sheet.utils.map
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMatchStatusUseCase @Inject constructor(
    private val repository: MatchRepository,
) {

    suspend operator fun invoke(matchId: UUID): Result<Boolean> {
        return repository.getById(matchId)
            .map(Match::isFinished)
    }
}