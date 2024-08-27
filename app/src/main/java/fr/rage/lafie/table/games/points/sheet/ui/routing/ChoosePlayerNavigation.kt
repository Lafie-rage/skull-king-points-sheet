package fr.rage.lafie.table.games.points.sheet.ui.routing

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import fr.rage.lafie.table.games.points.sheet.ui.page.player.choose.ChoosePlayerPage
import java.util.UUID


fun NavGraphBuilder.choosePlayerNavigation() {
    composable<ChoosePlayer> { backEntry ->
        ChoosePlayerPage(routeParams = backEntry.toRoute())
    }
}

fun NavController.navigateToChoosePlayer(
    gameId: UUID,
    matchId: UUID,
    roundIndex: Int,
) {
    navigate(
        ChoosePlayer(
            gameId = gameId,
            matchId = matchId,
            roundIndex = roundIndex,
        )
    )
}