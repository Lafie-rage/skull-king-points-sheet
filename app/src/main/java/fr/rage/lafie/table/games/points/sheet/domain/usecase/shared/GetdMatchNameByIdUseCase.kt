package fr.rage.lafie.table.games.points.sheet.domain.usecase.shared

import fr.rage.lafie.table.games.points.sheet.data.model.Match
import fr.rage.lafie.table.games.points.sheet.data.repository.MatchRepository
import fr.rage.lafie.table.games.points.sheet.utils.Result
import fr.rage.lafie.table.games.points.sheet.utils.map
import org.koin.core.annotation.Single
import java.util.UUID

/**
 * Retrieve the name of a match using the provided ID.
 */
@Single
class GetdMatchNameByIdUseCase(
    private val repository: MatchRepository,
) {

    suspend operator fun invoke(matchId: UUID): Result<String> {
        return repository.getById(matchId).map(Match::name)
    }
}