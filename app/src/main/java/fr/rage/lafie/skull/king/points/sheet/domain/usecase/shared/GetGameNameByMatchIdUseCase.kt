package fr.rage.lafie.skull.king.points.sheet.domain.usecase.shared

import fr.rage.lafie.skull.king.points.sheet.data.model.Game
import fr.rage.lafie.skull.king.points.sheet.data.model.Match
import fr.rage.lafie.skull.king.points.sheet.data.repository.GameRepository
import fr.rage.lafie.skull.king.points.sheet.data.repository.MatchRepository
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import fr.rage.lafie.skull.king.points.sheet.utils.flatMap
import fr.rage.lafie.skull.king.points.sheet.utils.map
import org.koin.core.annotation.Single
import java.util.UUID

@Single
class GetGameNameByMatchIdUseCase(
    private val gameRepository: GameRepository,
    private val matchRepository: MatchRepository,
) {

    suspend operator fun invoke(matchId: UUID): Result<String> =
        matchRepository.getById(matchId)
            .map(Match::gameId)
            .flatMap(gameRepository::getById)
            .map(Game::name)
}