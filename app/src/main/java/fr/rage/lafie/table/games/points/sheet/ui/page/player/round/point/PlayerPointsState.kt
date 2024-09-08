package fr.rage.lafie.table.games.points.sheet.ui.page.player.round.point

import java.util.UUID

data class PlayerPointsState(
    val id: UUID,
    val playerId: UUID,
    val roundIndex: Int,
    val points: Long,
)
