package fr.rage.lafie.table.games.points.sheet.domain.usecase.shared

import fr.rage.lafie.table.games.points.sheet.data.model.Game
import fr.rage.lafie.table.games.points.sheet.data.repository.GameRepository
import fr.rage.lafie.table.games.points.sheet.utils.Result
import fr.rage.lafie.table.games.points.sheet.utils.map
import org.koin.core.annotation.Single
import java.util.UUID

@Single
class GetGameNameByIdUseCase(
    private val repository: GameRepository,
) {

    suspend operator fun invoke(gameId: UUID): Result<String> = repository.getById(gameId)
        .map(Game::name)
}