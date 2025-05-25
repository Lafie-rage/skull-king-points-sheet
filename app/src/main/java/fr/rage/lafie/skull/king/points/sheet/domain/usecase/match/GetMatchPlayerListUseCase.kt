package fr.rage.lafie.skull.king.points.sheet.domain.usecase.match

import fr.rage.lafie.skull.king.points.sheet.data.model.Player
import fr.rage.lafie.skull.king.points.sheet.data.repository.PlayerRepository
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMatchPlayerListUseCase @Inject constructor(
    private val playerRepository: PlayerRepository,
) {

    operator fun invoke(matchId: UUID): Flow<Result<List<Player>>> =
        playerRepository.getAllByMatchId(matchId)
}