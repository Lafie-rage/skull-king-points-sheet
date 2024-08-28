package fr.rage.lafie.table.games.points.sheet.ui.routing.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import fr.rage.lafie.table.games.points.sheet.ui.page.player.round.point.PlayerRoundPointPage
import fr.rage.lafie.table.games.points.sheet.ui.routing.ChoosePlayerRoute
import fr.rage.lafie.table.games.points.sheet.ui.routing.PlayerRoundPointRoute
import java.util.UUID

fun NavGraphBuilder.playerRoundPointNavigation() {
    composable<PlayerRoundPointRoute> { backEntry ->
        PlayerRoundPointPage(
            routeParams = backEntry.toRoute(),
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