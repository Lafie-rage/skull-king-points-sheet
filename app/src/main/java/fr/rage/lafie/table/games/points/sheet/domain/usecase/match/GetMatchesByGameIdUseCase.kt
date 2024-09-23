package fr.rage.lafie.table.games.points.sheet.domain.usecase.match

import fr.rage.lafie.table.games.points.sheet.data.exception.EntityNotFoundById
import fr.rage.lafie.table.games.points.sheet.data.model.Game
import fr.rage.lafie.table.games.points.sheet.data.model.Match
import fr.rage.lafie.table.games.points.sheet.data.model.Player
import fr.rage.lafie.table.games.points.sheet.data.repository.GameRepository
import fr.rage.lafie.table.games.points.sheet.data.repository.MatchRepository
import fr.rage.lafie.table.games.points.sheet.data.repository.PlayerRepository
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
class GetMatchesByGameIdUseCase(
    private val gameRepository: GameRepository,
    private val repository: MatchRepository,
    private val playerRepository: PlayerRepository,
) {

    operator fun invoke(gameId: UUID): Flow<Result<List<Match>>> {
        return repository.getAllByGameId(gameId).map {
            if (it.isSuccess && it.getOrNull()?.isEmpty() != false) {
                initPlayers(gameId)
            }
            it
        }
    }

    private suspend fun initPlayers(gameId: UUID) {
        createMatchIfNotExisting(gameId).map { match ->
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
                matchId = match.id,
            )
        }
    }

    private suspend fun createMatchIfNotExisting(gameId: UUID): Result<Match> {
        return repository.getById(gameId).let { retrievedMatch ->
            if (retrievedMatch.isError && retrievedMatch.getExceptionOrNull() is EntityNotFoundById) {
                createGame(gameId)
                return repository.createOrUpdate(
                    match = Match(
                        id = UUID.fromString("c07b822c-dbd5-41ec-a15a-5bf55c3bd1bb"),
                        gameId = gameId,
                        name = "Match 1",
                        roundCounter = 1,
                    ),
                )
            }
            retrievedMatch
        }
    }

    private suspend fun createGame(gameId: UUID) {
        gameRepository.create(
            game = Game(
                id = gameId,
                name = "Skull King",
                minPlayers = 2,
                maxPlayers = 8,
                maxRounds = 10,
                shouldSumUpRoundsScores = true,
            )
        )
    }

}