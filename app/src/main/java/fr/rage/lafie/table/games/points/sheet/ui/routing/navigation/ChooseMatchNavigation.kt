package fr.rage.lafie.table.games.points.sheet.ui.routing.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fr.rage.lafie.table.games.points.sheet.ui.page.match.ChooseMatchPage
import fr.rage.lafie.table.games.points.sheet.ui.routing.ChooseMatchRoute
import java.util.UUID

fun NavGraphBuilder.chooseMatchNavigation(
    navigateToChooseRoundPage: (UUID) -> Unit,
    onBackPressed: () -> Unit,
) {
    composable<ChooseMatchRoute> {
        ChooseMatchPage(
            onMatchSelected = navigateToChooseRoundPage,
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