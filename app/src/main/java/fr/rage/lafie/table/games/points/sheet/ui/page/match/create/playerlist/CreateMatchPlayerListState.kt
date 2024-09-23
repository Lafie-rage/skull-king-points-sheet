package fr.rage.lafie.table.games.points.sheet.ui.page.match.create.playerlist

import java.util.UUID

data class CreateMatchPlayerListState(
    val title: String,
    val matchId: UUID = UUID.randomUUID(),
    val playersName: List<String>,
)