package fr.rage.lafie.skull.king.points.sheet.data.model

import java.util.UUID

data class Game(
    val id: UUID,
    val name: String,
    val minPlayers: Int,
    val maxPlayers: Int,
    val maxRounds: Int,
    val shouldSumUpRoundsScores: Boolean,
)
