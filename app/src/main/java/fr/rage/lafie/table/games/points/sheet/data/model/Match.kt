package fr.rage.lafie.table.games.points.sheet.data.model

import java.util.UUID

data class Match(
    val id: UUID,
    val name: String,
    val roundCounter: Int,
)