package fr.rage.lafie.table.games.points.sheet.ui.routing.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import fr.rage.lafie.table.games.points.sheet.ui.routing.CreateMatchMainInfosRoute
import fr.rage.lafie.table.games.points.sheet.ui.routing.CreateMatchRoute
import java.util.UUID

fun NavGraphBuilder.createMatchNavigation(

) {
    navigation<CreateMatchRoute>(startDestination = CreateMatchMainInfosRoute) {
        
    }
}

fun NavController.navigateToCreateMatchPage(
    gameId: UUID,
) {
    navigate(
        CreateMatchRoute(
            gameId = gameId.toString(),
        )
    )
}