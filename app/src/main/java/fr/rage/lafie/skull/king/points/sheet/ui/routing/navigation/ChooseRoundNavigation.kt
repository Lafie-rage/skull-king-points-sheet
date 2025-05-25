package fr.rage.lafie.skull.king.points.sheet.ui.routing.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import fr.rage.lafie.skull.king.points.sheet.ui.page.round.ChooseRoundPage
import fr.rage.lafie.skull.king.points.sheet.ui.routing.ChooseMatchRoute
import fr.rage.lafie.skull.king.points.sheet.ui.routing.ChooseRoundRoute
import java.util.UUID

fun NavGraphBuilder.chooseRoundNavigation(
    navigateToChoosePlayerPage: (UUID, Int) -> Unit,
    navigateToMatchRecap: (UUID) -> Unit,
    onBackPressed: () -> Unit,
) {
    composable<ChooseRoundRoute> { backStackEntry ->
        val routeParams: ChooseRoundRoute = backStackEntry.toRoute()
        val matchId = UUID.fromString(routeParams.matchId)

        ChooseRoundPage(
            onRoundSelected = { roundIndex ->
                navigateToChoosePlayerPage(
                    matchId,
                    roundIndex,
                )
            },
            navigateToMatchRecap = {
                navigateToMatchRecap(matchId)
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
    ) {
        popUpTo(ChooseMatchRoute) {
            inclusive = true
        }
    }
}