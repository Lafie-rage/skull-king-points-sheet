package fr.rage.lafie.table.games.points.sheet.ui.routing

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import fr.rage.lafie.table.games.points.sheet.ui.routing.navigation.chooseMatchNavigation
import fr.rage.lafie.table.games.points.sheet.ui.routing.navigation.choosePlayerNavigation
import fr.rage.lafie.table.games.points.sheet.ui.routing.navigation.chooseRoundNavigation
import fr.rage.lafie.table.games.points.sheet.ui.routing.navigation.createMatchNavigation
import fr.rage.lafie.table.games.points.sheet.ui.routing.navigation.navigateToChoosePlayerPage
import fr.rage.lafie.table.games.points.sheet.ui.routing.navigation.navigateToChooseRoundPage
import fr.rage.lafie.table.games.points.sheet.ui.routing.navigation.navigateToCreateMatchPage
import fr.rage.lafie.table.games.points.sheet.ui.routing.navigation.navigateToPlayerPointsPage
import fr.rage.lafie.table.games.points.sheet.ui.routing.navigation.playerPointNavigation

@Composable
fun Router() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ChooseMatchRoute(
            "2c3466fc-d033-460f-9020-5d89cbfc2c55",
        ),
    ) {
        createMatchNavigation(

        )

        chooseMatchNavigation(
            navigateToChooseRoundPage = navController::navigateToChooseRoundPage,
            navigateToCreateMachPage = navController::navigateToCreateMatchPage,
            onBackPressed = navController::navigateUp
        )

        chooseRoundNavigation(
            navigateToChoosePlayerPage = navController::navigateToChoosePlayerPage,
            onBackPressed = navController::navigateUp,
        )

        choosePlayerNavigation(
            navigateToPlayerPointPage = navController::navigateToPlayerPointsPage,
            onBackPressed = navController::navigateUp,
        )

        playerPointNavigation(
            onBackPressed = navController::navigateUp,
        )
    }


}