package fr.rage.lafie.skull.king.points.sheet.ui.routing.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fr.rage.lafie.skull.king.points.sheet.ui.page.match.choose.ChooseMatchPage
import fr.rage.lafie.skull.king.points.sheet.ui.routing.ChooseMatchRoute
import java.util.UUID

fun NavGraphBuilder.chooseMatchNavigation(
    navigateToChooseRoundPage: (UUID) -> Unit,
    navigateToCreateMachPage: () -> Unit,
    onBackPressed: () -> Unit,
) {
    composable<ChooseMatchRoute> {
        ChooseMatchPage(
            onMatchSelected = navigateToChooseRoundPage,
            onCreateNewMatchClicked = {
                navigateToCreateMachPage()
            },
            onBackPressed = onBackPressed,
        )
    }
}