package fr.rage.lafie.table.games.points.sheet.domain.usecase.shared

import fr.rage.lafie.table.games.points.sheet.data.model.Game
import fr.rage.lafie.table.games.points.sheet.data.model.Match
import fr.rage.lafie.table.games.points.sheet.data.repository.GameRepository
import fr.rage.lafie.table.games.points.sheet.data.repository.MatchRepository
import fr.rage.lafie.table.games.points.sheet.utils.Result
import fr.rage.lafie.table.games.points.sheet.utils.flatMap
import fr.rage.lafie.table.games.points.sheet.utils.map
import fr.rage.lafie.table.games.points.sheet.utils.zip
import org.koin.core.annotation.Single
import java.util.UUID

/**
 * Retrieve the title of the App following a pattern [Game Name] - [Match Name].
 */
@Single
class GetPageTitleUsingMatchAndGameNamesByMatchIdUseCase(
    private val matchRepository: MatchRepository,
    private val gameRepository: GameRepository,
) {

    suspend operator fun invoke(matchId: UUID): Result<String> {
        val matchResult = matchRepository.getById(matchId)
        val gameName = matchResult.map(Match::id)
            .flatMap(gameRepository::getById)
            .map(Game::name)
        return matchResult.map(Match::name)
            .zip(gameName)
            .map { (matchName, gameName) ->
                "$gameName - $matchName"
            }
    }
}