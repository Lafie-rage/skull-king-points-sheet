package fr.rage.lafie.table.games.points.sheet.ui.page.player.choose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.rage.lafie.table.games.points.sheet.ui.component.ChoosePlayerButton
import fr.rage.lafie.table.games.points.sheet.ui.routing.ChoosePlayerRoute
import fr.rage.lafie.table.games.points.sheet.ui.theme.TableGamesPointsSheetTheme
import fr.rage.lafie.table.games.points.sheet.utils.process
import org.koin.androidx.compose.koinViewModel
import java.util.UUID

@Composable
fun ChoosePlayerPage(
    routeParams: ChoosePlayerRoute,
    onNavigateToPlayer: (PlayerState) -> Unit,
    viewModel: ChoosePlayerViewModel = koinViewModel(),
) {
    val players by viewModel.players.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.setMatchId(matchId = UUID.fromString(routeParams.matchId))
    }

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            players.process(onSuccess = {
                PlayerList(
                    players = it,
                    onNavigateToPlayer = onNavigateToPlayer
                )
            })
        }
    }
}

@Composable
private fun PlayerList(
    players: List<PlayerState>,
    onNavigateToPlayer: (PlayerState) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(players) { player ->
            ChoosePlayerButton(
                player,
                onNavigateToPlayer
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ChoosePlayerPagePreview() {
    TableGamesPointsSheetTheme {
        PlayerList(
            players = listOf(
                PlayerState(
                    id = UUID.randomUUID(),
                    name = "Player 1"
                ),
                PlayerState(
                    id = UUID.randomUUID(),
                    name = "Player 2"
                ),
                PlayerState(
                    id = UUID.randomUUID(),
                    name = "Player 3"
                ),
                PlayerState(
                    id = UUID.randomUUID(),
                    name = "Player 4"
                ),
            ),
            onNavigateToPlayer = {}
        )
    }
}