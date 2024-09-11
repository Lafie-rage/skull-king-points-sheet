package fr.rage.lafie.table.games.points.sheet.ui.routing.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import fr.rage.lafie.table.games.points.sheet.ui.page.player.choose.ChoosePlayerPage
import fr.rage.lafie.table.games.points.sheet.ui.routing.ChoosePlayerRoute
import java.util.UUID


fun NavGraphBuilder.choosePlayerNavigation(
    onNavigateToPlayer: (UUID, Int) -> Unit,
    onNavigateBack: () -> Unit,
) {
    composable<ChoosePlayerRoute> { backEntry ->
        val routeParams: ChoosePlayerRoute = backEntry.toRoute()
        ChoosePlayerPage(
            onNavigateToPlayer = { player ->
                onNavigateToPlayer(player.id, routeParams.roundIndex)
            },
            onNavigateBack = onNavigateBack,
        )
    }
}

fun NavController.navigateToChoosePlayer(
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