package fr.rage.lafie.table.games.points.sheet.ui.routing.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fr.rage.lafie.table.games.points.sheet.ui.page.player.round.point.PlayerRoundPointPage
import fr.rage.lafie.table.games.points.sheet.ui.routing.PlayerRoundPointRoute
import java.util.UUID

fun NavGraphBuilder.playerRoundPointNavigation(
    onNavigateBack: () -> Unit,
) {
    composable<PlayerRoundPointRoute> {
        PlayerRoundPointPage(
            onNavigateBack = onNavigateBack,
        )
    }
}

fun NavController.navigateToPlayerRoundPoints(
    playerId: UUID,
    roundIndex: Int,
) {
    navigate(
        PlayerRoundPointRoute(
            playerId = playerId.toString(),
            roundIndex = roundIndex,
        )
    )
}