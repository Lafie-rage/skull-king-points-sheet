package fr.rage.lafie.skull.king.points.sheet.domain.usecase.match

import fr.rage.lafie.skull.king.points.sheet.data.model.Match
import fr.rage.lafie.skull.king.points.sheet.data.repository.MatchRepository
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMatchesByGameIdUseCase @Inject constructor(
    private val repository: MatchRepository,
) {

    operator fun invoke(): Flow<Result<List<Match>>> {
        return repository.getAll()
    }

}