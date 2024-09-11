package fr.rage.lafie.table.games.points.sheet.ui.routing.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import fr.rage.lafie.table.games.points.sheet.ui.page.round.ChooseRoundPage
import fr.rage.lafie.table.games.points.sheet.ui.routing.ChooseRoundRoute
import java.util.UUID

fun NavGraphBuilder.chooseRoundNavigation(
    onNavigateToChoosePlayerPage: (UUID, Int) -> Unit,
    onNavigateBack: () -> Unit,
) {
    composable<ChooseRoundRoute> { backStackEntry ->
        val routeParams: ChooseRoundRoute = backStackEntry.toRoute()

        ChooseRoundPage(
            onNavigateToRound = { roundIndex ->
                onNavigateToChoosePlayerPage(
                    UUID.fromString(routeParams.matchId),
                    roundIndex
                )
            },
            onNavigateBack = onNavigateBack,
        )
    }
}