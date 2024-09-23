package fr.rage.lafie.table.games.points.sheet.ui.page.round.state

data class ChooseRoundState(
    val title: String,
    val maxRound: Int,
    val rounds: List<RoundState>,
)
