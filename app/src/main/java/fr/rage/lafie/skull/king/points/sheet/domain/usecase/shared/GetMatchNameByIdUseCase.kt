package fr.rage.lafie.skull.king.points.sheet.domain.usecase.shared

import fr.rage.lafie.skull.king.points.sheet.data.model.Match
import fr.rage.lafie.skull.king.points.sheet.data.repository.MatchRepository
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import fr.rage.lafie.skull.king.points.sheet.utils.map
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Retrieve the name of a match using the provided ID.
 */
@Singleton
class GetMatchNameByIdUseCase @Inject constructor(
    private val repository: MatchRepository,
) {

    suspend operator fun invoke(matchId: UUID): Result<String> {
        return repository.getById(matchId).map(Match::name)
    }
}