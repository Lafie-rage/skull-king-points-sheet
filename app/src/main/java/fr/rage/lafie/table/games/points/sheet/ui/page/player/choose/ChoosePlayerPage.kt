package fr.rage.lafie.table.games.points.sheet.ui.page.player.choose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import fr.rage.lafie.table.games.points.sheet.ui.routing.ChoosePlayer
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChoosePlayerPage(
    routeParams: ChoosePlayer,
    viewModel: ChoosePlayerViewModel = koinViewModel(),
) {
    val players = viewModel.players

    LaunchedEffect(Unit) {
        viewModel
    }
}