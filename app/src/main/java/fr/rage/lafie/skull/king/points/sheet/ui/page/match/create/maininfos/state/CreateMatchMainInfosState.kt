package fr.rage.lafie.skull.king.points.sheet.ui.page.match.create.maininfos.state

data class CreateMatchMainInfosState(
    val matchName: String = "",
    val playersCount: Int = 1,
    val minPlayers: Int,
    val maxPlayers: Int,
)