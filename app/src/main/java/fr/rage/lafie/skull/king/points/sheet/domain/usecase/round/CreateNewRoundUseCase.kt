package fr.rage.lafie.skull.king.points.sheet.domain.usecase.round

import fr.rage.lafie.skull.king.points.sheet.data.repository.MatchRepository
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import fr.rage.lafie.skull.king.points.sheet.utils.flatMap
import fr.rage.lafie.skull.king.points.sheet.utils.then
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateNewRoundUseCase @Inject constructor(
    val repository: MatchRepository,
) {
    suspend operator fun invoke(
        matchId: UUID,
    ): Result<Unit> {
        return repository.getById(matchId)
            .flatMap { match ->
                repository.createOrUpdate(match.copy(roundCounter = match.roundCounter + 1))
            }.then()
    }
}