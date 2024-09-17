package fr.rage.lafie.table.games.points.sheet.ui.routing.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import fr.rage.lafie.table.games.points.sheet.ui.page.round.ChooseRoundPage
import fr.rage.lafie.table.games.points.sheet.ui.routing.ChooseRoundRoute
import java.util.UUID

fun NavGraphBuilder.chooseRoundNavigation(
    navigateToChoosePlayerPage: (UUID, Int) -> Unit,
    onBackPressed: () -> Unit,
) {
    composable<ChooseRoundRoute> { backStackEntry ->
        val routeParams: ChooseRoundRoute = backStackEntry.toRoute()

        ChooseRoundPage(
            onRoundSelected = { roundIndex ->
                navigateToChoosePlayerPage(
                    UUID.fromString(routeParams.matchId),
                    roundIndex
                )
            },
            onBackPressed = onBackPressed,
        )
    }
}

fun NavController.navigateToChooseRoundPage(
    matchId: UUID,
) {
    navigate(
        ChooseRoundRoute(
            matchId = matchId.toString(),
        )
    )
}