package fr.rage.lafie.skull.king.points.sheet.ui.page.round.state

data class ChooseRoundState(
    val title: String,
    val isMatchFinished: Boolean,
    val rounds: List<RoundState>,
)
