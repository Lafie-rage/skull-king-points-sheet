package fr.rage.lafie.table.games.points.sheet.ui.routing

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import fr.rage.lafie.table.games.points.sheet.ui.routing.navigation.choosePlayerNavigation
import fr.rage.lafie.table.games.points.sheet.ui.routing.navigation.navigateToPlayerRoundPoints
import fr.rage.lafie.table.games.points.sheet.ui.routing.navigation.playerRoundPointNavigation

@Composable
fun Router() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = ChoosePlayerRoute(
            "c07b822c-dbd5-41ec-a15a-5bf55c3bd1bb",
            0,
        )
    ) {
        choosePlayerNavigation(
            onNavigateToPlayer = { player, roundIndex ->
                navController.navigateToPlayerRoundPoints(player.id, roundIndex)
            }
        )
        playerRoundPointNavigation(
            onNavigateBack = navController::popBackStack
        )
    }


}