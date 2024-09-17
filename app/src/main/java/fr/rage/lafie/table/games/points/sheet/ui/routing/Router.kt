package fr.rage.lafie.table.games.points.sheet.ui.routing

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import fr.rage.lafie.table.games.points.sheet.ui.routing.navigation.chooseMatchNavigation
import fr.rage.lafie.table.games.points.sheet.ui.routing.navigation.choosePlayerNavigation
import fr.rage.lafie.table.games.points.sheet.ui.routing.navigation.chooseRoundNavigation
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
            "c07b822c-dbd5-41ec-a15a-5bf55c3bd1bb",
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