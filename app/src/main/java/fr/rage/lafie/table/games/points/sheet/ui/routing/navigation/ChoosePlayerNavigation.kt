package fr.rage.lafie.table.games.points.sheet.ui.routing.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import fr.rage.lafie.table.games.points.sheet.ui.page.player.choose.ChoosePlayerPage
import fr.rage.lafie.table.games.points.sheet.ui.routing.ChoosePlayerRoute
import java.util.UUID


fun NavGraphBuilder.choosePlayerNavigation() {
    composable<ChoosePlayerRoute> { backEntry ->
        ChoosePlayerPage(
            routeParams = backEntry.toRoute(),
            onNavigateToPlayer = {},
        )
    }
}

fun NavController.navigateToChoosePlayer(
    gameId: UUID,
    matchId: UUID,
    roundIndex: Int,
) {
    navigate(
        ChoosePlayerRoute(
            matchId = matchId.toString(),
            roundIndex = roundIndex,
        )
    )
}