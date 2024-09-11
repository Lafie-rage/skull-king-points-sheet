package fr.rage.lafie.table.games.points.sheet.domain.usecase.match

import fr.rage.lafie.table.games.points.sheet.data.model.Match
import fr.rage.lafie.table.games.points.sheet.data.repository.MatchRepository
import fr.rage.lafie.table.games.points.sheet.utils.Result
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single
import java.util.UUID

@Single
class GetMatchesByGameIdUseCase(
    private val repository: MatchRepository,
) {

    operator fun invoke(gameId: UUID): Flow<Result<List<Match>>> {
        return repository.getAllByGameId(gameId)
    }

}