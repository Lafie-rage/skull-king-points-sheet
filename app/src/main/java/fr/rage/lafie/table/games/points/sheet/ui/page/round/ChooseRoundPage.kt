package fr.rage.lafie.table.games.points.sheet.ui.page.round

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import fr.rage.lafie.table.games.points.sheet.ui.component.ChooseItemList
import fr.rage.lafie.table.games.points.sheet.ui.component.core.appbar.AppBar
import fr.rage.lafie.table.games.points.sheet.ui.page.round.state.RoundState
import fr.rage.lafie.table.games.points.sheet.ui.theme.TableGamesPointsSheetTheme
import fr.rage.lafie.table.games.points.sheet.utils.MapToComposable
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChooseRoundPage(
    onRoundSelected: (Int) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: ChooseRoundViewModel = koinViewModel(),
) {
    val state by viewModel.state

    state.MapToComposable(
        onSuccess = {
            Page(
                it.title,
                it.rounds,
                onRoundSelected,
                onBackPressed,
            )
        }
    )
}

@Composable
private fun Page(
    title: String,
    rounds: List<RoundState>,
    onNavigateToRound: (Int) -> Unit,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppBar(
                title = title,
                onBackPressed = onBackPressed,
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            ChooseItemList(
                items = rounds.map { it.index },
                getItemLabel = { it.toString() },
                onItemSelected = onNavigateToRound,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChooseRoundPagePreview() {
    val context = LocalContext.current

    TableGamesPointsSheetTheme {
        Page(
            title = "Skull King - Match 1",
            rounds = listOf(
                RoundState(1),
                RoundState(2),
                RoundState(3),
                RoundState(4),
            ),
            onNavigateToRound = { roundIndex ->
                Toast.makeText(context, "Navigate to round $roundIndex", Toast.LENGTH_LONG).show()
            },
            onBackPressed = {
                Toast.makeText(context, "Navigate back", Toast.LENGTH_LONG).show()
            }
        )
    }
}