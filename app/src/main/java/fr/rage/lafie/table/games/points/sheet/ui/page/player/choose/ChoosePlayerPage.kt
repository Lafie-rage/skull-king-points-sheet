package fr.rage.lafie.table.games.points.sheet.ui.page.player.choose

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.rage.lafie.table.games.points.sheet.ui.component.ChooseItemGridList
import fr.rage.lafie.table.games.points.sheet.ui.component.core.appbar.AppBar
import fr.rage.lafie.table.games.points.sheet.ui.theme.TableGamesPointsSheetTheme
import fr.rage.lafie.table.games.points.sheet.utils.MapToComposable
import org.koin.androidx.compose.koinViewModel
import java.util.UUID

@Composable
fun ChoosePlayerPage(
    onNavigateToPlayer: (PlayerState) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: ChoosePlayerViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    state.MapToComposable(onSuccess = {
        Page(
            title = it.title,
            players = it.players,
            onNavigateToPlayer = onNavigateToPlayer,
            onNavigateBack = onNavigateBack,
        )
    })
}

@Composable
private fun Page(
    title: String,
    players: List<PlayerState>,
    onNavigateToPlayer: (PlayerState) -> Unit,
    onNavigateBack: () -> Unit,
) {
    Scaffold(topBar = {
        AppBar(
            title = title,
            onNavigateBack = onNavigateBack,
        )
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            ChooseItemGridList(
                items = players,
                getItemLabel = { it.name },
                onItemSelected = onNavigateToPlayer,
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ChoosePlayerPagePreview() {
    val context = LocalContext.current

    TableGamesPointsSheetTheme {
        Page(
            title = "Skull King - Match 1",
            players = listOf(
                PlayerState(id = UUID.randomUUID(), name = "Player 1"),
                PlayerState(id = UUID.randomUUID(), name = "Player 2"),
                PlayerState(id = UUID.randomUUID(), name = "Player 3"),
                PlayerState(id = UUID.randomUUID(), name = "Player 4"),
            ),
            onNavigateToPlayer = { player ->
                Toast.makeText(context, "Navigate to player ${player.name}", Toast.LENGTH_LONG)
                    .show()
            },
            onNavigateBack = {
                Toast.makeText(context, "Navigate back", Toast.LENGTH_LONG).show()
            },
        )
    }
}