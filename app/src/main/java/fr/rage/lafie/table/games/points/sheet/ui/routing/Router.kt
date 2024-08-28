package fr.rage.lafie.table.games.points.sheet.ui.routing

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import fr.rage.lafie.table.games.points.sheet.ui.routing.navigation.choosePlayerNavigation
import java.util.UUID

@Composable
fun Router() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = ChoosePlayerRoute(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            0,
        )
    ) {
        choosePlayerNavigation()
    }


}