package fr.rage.lafie.skull.king.points.sheet.ui.routing.navigation.creatematch

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fr.rage.lafie.skull.king.points.sheet.ui.page.match.create.maininfos.CreateMatchMainInfosPage
import fr.rage.lafie.skull.king.points.sheet.ui.routing.CreateMatchMainInfosRoute

fun NavGraphBuilder.createMatchMainInfosNavigation(
    navigateToCreateMatchPlayerListPage: (String, Int) -> Unit,
    onBackPressed: () -> Unit,
) {
    composable<CreateMatchMainInfosRoute> {
        CreateMatchMainInfosPage(
            onNavigateToNextPage = navigateToCreateMatchPlayerListPage,
            onBackPressed = onBackPressed
        )
    }
}