package fr.rage.lafie.skull.king.points.sheet.ui.routing.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import fr.rage.lafie.skull.king.points.sheet.ui.routing.CreateMatchMainInfosRoute
import fr.rage.lafie.skull.king.points.sheet.ui.routing.CreateMatchRoute
import fr.rage.lafie.skull.king.points.sheet.ui.routing.navigation.creatematch.createMatchMainInfosNavigation
import fr.rage.lafie.skull.king.points.sheet.ui.routing.navigation.creatematch.createMatchPlayerListNavigation
import java.util.UUID

fun NavGraphBuilder.createMatchNavigation(
    navigateToCreateMatchPlayerListPage: (String, Int) -> Unit,
    navigateToChooseRoundPage: (UUID) -> Unit,
    onBackPressed: () -> Unit,
) {
    navigation<CreateMatchRoute>(startDestination = CreateMatchMainInfosRoute) {
        createMatchMainInfosNavigation(
            navigateToCreateMatchPlayerListPage = navigateToCreateMatchPlayerListPage,
            onBackPressed = onBackPressed,
        )

        createMatchPlayerListNavigation(
            navigateToChooseRoundPage = navigateToChooseRoundPage,
            onBackPressed = onBackPressed,
        )
    }
}

fun NavController.navigateToCreateMatchPage() {
    navigate(CreateMatchRoute)
}