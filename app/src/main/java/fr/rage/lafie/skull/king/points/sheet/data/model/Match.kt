package fr.rage.lafie.skull.king.points.sheet.data.model

import java.util.UUID

data class Match(
    val id: UUID,
    val gameId: UUID,
    val name: String,
    val roundCounter: Int,
    val isFinished: Boolean,
)
