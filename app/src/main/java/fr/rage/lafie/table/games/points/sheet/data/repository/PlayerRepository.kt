package fr.rage.lafie.table.games.points.sheet.data.repository

import fr.rage.lafie.table.games.points.sheet.data.model.Player
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface PlayerRepository {
    fun getAllByMatchId(matchId: UUID): Flow<List<Player>>
}