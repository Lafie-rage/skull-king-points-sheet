package fr.rage.lafie.skull.king.points.sheet.domain.usecase.shared

import fr.rage.lafie.skull.king.points.sheet.data.model.Match
import fr.rage.lafie.skull.king.points.sheet.data.repository.MatchRepository
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import fr.rage.lafie.skull.king.points.sheet.utils.map
import org.koin.core.annotation.Single
import java.util.UUID

/**
 * Retrieve the name of a match using the provided ID.
 */
@Single
class GetMatchNameByIdUseCase(
    private val repository: MatchRepository,
) {

    suspend operator fun invoke(matchId: UUID): Result<String> {
        return repository.getById(matchId).map(Match::name)
    }
}