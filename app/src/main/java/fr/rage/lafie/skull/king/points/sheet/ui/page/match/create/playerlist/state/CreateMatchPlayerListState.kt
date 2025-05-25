package fr.rage.lafie.skull.king.points.sheet.ui.page.match.create.playerlist.state

import java.util.UUID

data class CreateMatchPlayerListState(
    val title: String,
    val matchId: UUID = UUID.randomUUID(),
    val playersName: List<String>,
)