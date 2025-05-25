package fr.rage.lafie.skull.king.points.sheet.ui.page.match.recap.state

data class PlayerAndScoreState(
    val name: String,
    val roundScores: List<Long>,
) {
    val totalScore: Long
        get() = roundScores.sum()
}
