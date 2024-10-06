package fr.rage.lafie.skull.king.points.sheet.ui.page.player.point.state

import java.util.UUID

data class PlayerPointsState(
    val id: UUID,
    val playerId: UUID,
    val roundIndex: Int,
    val points: Long,
)
