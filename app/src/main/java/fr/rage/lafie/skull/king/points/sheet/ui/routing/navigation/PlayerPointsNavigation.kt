package fr.rage.lafie.skull.king.points.sheet.ui.routing.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fr.rage.lafie.skull.king.points.sheet.ui.page.player.point.PlayerPointPage
import fr.rage.lafie.skull.king.points.sheet.ui.routing.PlayerPointRoute
import java.util.UUID

fun NavGraphBuilder.playerPointNavigation(
    onBackPressed: () -> Unit,
) {
    composable<PlayerPointRoute> {
        PlayerPointPage(
            onBackPressed = onBackPressed,
        )
    }
}

fun NavController.navigateToPlayerPointsPage(
    playerId: UUID,
    roundIndex: Int,
) {
    navigate(
        PlayerPointRoute(
            playerId = playerId.toString(),
            roundIndex = roundIndex,
        )
    )
}