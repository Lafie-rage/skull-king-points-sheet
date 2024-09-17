package fr.rage.lafie.table.games.points.sheet.ui.routing.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import fr.rage.lafie.table.games.points.sheet.ui.page.match.choose.ChooseMatchPage
import fr.rage.lafie.table.games.points.sheet.ui.routing.ChooseMatchRoute
import java.util.UUID

fun NavGraphBuilder.chooseMatchNavigation(
    navigateToChooseRoundPage: (UUID) -> Unit,
    navigateToCreateMachPage: (UUID) -> Unit,
    onBackPressed: () -> Unit,
) {
    composable<ChooseMatchRoute> { backEntry ->
        val routeParams: ChooseMatchRoute = backEntry.toRoute()
        ChooseMatchPage(
            onMatchSelected = navigateToChooseRoundPage,
            onCreateNewMatchClicked = {
                navigateToCreateMachPage(UUID.fromString(routeParams.gameId))
            },
            onBackPressed = onBackPressed,
        )
    }
}

fun NavController.navigateToChooseMatch(
    gameId: UUID,
) {
    navigate(
        ChooseMatchRoute(
            gameId = gameId.toString(),
        )
    )
}