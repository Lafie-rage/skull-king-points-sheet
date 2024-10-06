package fr.rage.lafie.skull.king.points.sheet.ui.page.match.choose.state

import java.util.UUID

data class MatchState(
    val id: UUID,
    val name: String,
    val isFinished: Boolean,
)
