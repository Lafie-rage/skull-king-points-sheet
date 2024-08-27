package fr.rage.lafie.table.games.points.sheet.ui.routing

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import java.util.UUID

@Composable
fun Router() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = ChoosePlayer(
            UUID.randomUUID(),
            UUID.randomUUID(),
            0,
        )
    ) {
        choosePlayerNavigation()
    }


}