package fr.rage.lafie.table.games.points.sheet.ui.page.player.choose.state

import fr.rage.lafie.table.games.points.sheet.ui.page.player.choose.PlayerState

data class ChoosePlayerState(
    val title: String,
    val players: List<PlayerState>,
)
