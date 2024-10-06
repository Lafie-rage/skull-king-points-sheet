package fr.rage.lafie.skull.king.points.sheet.domain.usecase.match

import fr.rage.lafie.skull.king.points.sheet.data.model.Match
import fr.rage.lafie.skull.king.points.sheet.data.repository.MatchRepository
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import fr.rage.lafie.skull.king.points.sheet.utils.map
import org.koin.core.annotation.Single
import java.util.UUID

@Single
class GetMatchStatusUseCase(
    private val repository: MatchRepository,
) {

    suspend operator fun invoke(matchId: UUID): Result<Boolean> {
        return repository.getById(matchId)
            .map(Match::isFinished)
    }
}