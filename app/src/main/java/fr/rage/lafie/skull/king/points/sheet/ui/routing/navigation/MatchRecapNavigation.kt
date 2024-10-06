package fr.rage.lafie.skull.king.points.sheet.ui.routing.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fr.rage.lafie.skull.king.points.sheet.ui.page.match.recap.MatchRecapPage
import fr.rage.lafie.skull.king.points.sheet.ui.routing.MatchRecapRoute
import java.util.UUID

fun NavGraphBuilder.matchRecapNavigation(
    onBackPressed: () -> Unit,
) {
    composable<MatchRecapRoute> {
        MatchRecapPage(
            onBackPressed = onBackPressed,
        )
    }
}

fun NavController.navigateToMatchRecap(matchId: UUID) {
    navigate(
        MatchRecapRoute(
            matchId = matchId.toString(),
        )
    )
}