package fr.rage.lafie.table.games.points.sheet.data.model

import java.util.UUID

data class Game(
    val id: UUID,
    val name: String,
    val minPlayers: Int,
    val maxPlayers: Int,
    val maxRounds: Int,
    val shouldSumUpRoundsScores: Boolean,
)
