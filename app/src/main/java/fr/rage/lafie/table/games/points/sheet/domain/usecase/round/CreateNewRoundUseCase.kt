package fr.rage.lafie.table.games.points.sheet.domain.usecase.round

import fr.rage.lafie.table.games.points.sheet.data.repository.MatchRepository
import fr.rage.lafie.table.games.points.sheet.utils.Result
import fr.rage.lafie.table.games.points.sheet.utils.flatMap
import fr.rage.lafie.table.games.points.sheet.utils.then
import org.koin.core.annotation.Single
import java.util.UUID

@Single
class CreateNewRoundUseCase(
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