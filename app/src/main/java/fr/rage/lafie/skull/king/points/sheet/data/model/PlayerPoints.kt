package fr.rage.lafie.skull.king.points.sheet.data.model

import java.util.UUID

data class PlayerPoints(
    val id: UUID,
    val playerId: UUID,
    val roundIndex: Int,
    val points: Long,
)
