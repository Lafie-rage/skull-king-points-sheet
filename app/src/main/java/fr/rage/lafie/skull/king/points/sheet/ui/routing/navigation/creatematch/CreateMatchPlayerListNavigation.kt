package fr.rage.lafie.skull.king.points.sheet.ui.routing.navigation.creatematch

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fr.rage.lafie.skull.king.points.sheet.ui.page.match.create.playerlist.CreateMatchPlayerListPage
import fr.rage.lafie.skull.king.points.sheet.ui.routing.CreateMatchPlayerListRoute
import java.util.UUID

fun NavGraphBuilder.createMatchPlayerListNavigation(
    navigateToChooseRoundPage: (UUID) -> Unit,
    onBackPressed: () -> Unit,
) {
    composable<CreateMatchPlayerListRoute> {
        CreateMatchPlayerListPage(
            onMatchCreated = navigateToChooseRoundPage,
            onBackPressed = onBackPressed,
        )
    }
}

fun NavController.navigateToCreateMatchPlayerListPage(
    matchName: String,
    playersCount: Int,
) {
    navigate(
        CreateMatchPlayerListRoute(
            matchName = matchName,
            playersCount = playersCount,
        )
    )
}