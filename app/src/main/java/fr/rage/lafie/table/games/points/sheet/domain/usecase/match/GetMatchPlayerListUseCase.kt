package fr.rage.lafie.table.games.points.sheet.domain.usecase.match

import fr.rage.lafie.table.games.points.sheet.data.exception.EntityNotFoundById
import fr.rage.lafie.table.games.points.sheet.data.model.Game
import fr.rage.lafie.table.games.points.sheet.data.model.Match
import fr.rage.lafie.table.games.points.sheet.data.model.Player
import fr.rage.lafie.table.games.points.sheet.data.repository.GameRepository
import fr.rage.lafie.table.games.points.sheet.data.repository.MatchRepository
import fr.rage.lafie.table.games.points.sheet.data.repository.PlayerRepository
import fr.rage.lafie.table.games.points.sheet.domain.mapper.toState
import fr.rage.lafie.table.games.points.sheet.ui.page.player.choose.PlayerState
import fr.rage.lafie.table.games.points.sheet.utils.Result
import fr.rage.lafie.table.games.points.sheet.utils.getExceptionOrNull
import fr.rage.lafie.table.games.points.sheet.utils.getOrNull
import fr.rage.lafie.table.games.points.sheet.utils.isError
import fr.rage.lafie.table.games.points.sheet.utils.isSuccess
import fr.rage.lafie.table.games.points.sheet.utils.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single
import java.util.UUID

@Single
class GetMatchPlayerListUseCase(
    private val playerRepository: PlayerRepository,
    private val matchRepository: MatchRepository,
    private val gameRepository: GameRepository,
) {

    operator fun invoke(matchId: UUID): Flow<Result<List<PlayerState>>> =
        playerRepository.getAllByMatchId(matchId).map {
            if (it.isSuccess && it.getOrNull()?.isEmpty() != false) {
                initPlayers(matchId)
            }
            it.map(List<Player>::toState)
        }

    private suspend fun initPlayers(matchId: UUID) {
        createMatchIfNotExisting(matchId)
        playerRepository.associateToMatch(
            players = listOf(
                Player(
                    id = UUID.randomUUID(),
                    name = "Emile",
                ),
                Player(
                    id = UUID.randomUUID(),
                    name = "Léo",
                ),
                Player(
                    id = UUID.randomUUID(),
                    name = "Valentin",
                ),
                Player(
                    id = UUID.randomUUID(),
                    name = "Corentin",
                ),
            ),
            matchId = matchId,
        )
    }

    private suspend fun createMatchIfNotExisting(matchId: UUID) {
        matchRepository.getById(matchId).let {
            if (it.isError && it.getExceptionOrNull() is EntityNotFoundById) {
                val gameId = UUID.randomUUID()
                createGame(gameId)
                matchRepository.create(
                    match = Match(
                        id = matchId,
                        name = "Match 1",
                        roundCounter = 1,
                    ),
                    gameId = gameId,
                )
            }
        }
    }

    private suspend fun createGame(gameId: UUID) {
        gameRepository.create(
            game = Game(
                id = gameId,
                name = "Skull King",
            )
        )
    }
}