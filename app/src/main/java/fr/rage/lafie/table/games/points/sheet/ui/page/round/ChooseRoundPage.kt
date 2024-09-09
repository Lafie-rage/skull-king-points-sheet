package fr.rage.lafie.table.games.points.sheet.ui.page.round

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.rage.lafie.table.games.points.sheet.ui.component.core.appbar.AppBar
import fr.rage.lafie.table.games.points.sheet.ui.page.round.state.RoundState
import fr.rage.lafie.table.games.points.sheet.ui.theme.TableGamesPointsSheetTheme
import fr.rage.lafie.table.games.points.sheet.utils.MapToComposable
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChooseRoundPage(
    onRoundSelected: (Int) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: ChooseRoundViewModel = koinViewModel(),
) {
    val state by viewModel.state

    state.MapToComposable(
        onSuccess = {
            Page(
                it.title,
                it.rounds,
                onRoundSelected,
                onNavigateBack,
            )
        }
    )
}

@Composable
private fun Page(
    title: String,
    rounds: List<RoundState>,
    onRoundSelected: (Int) -> Unit,
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppBar(
                title = title,
                onNavigateBack = onNavigateBack,
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
        }
    }
}

@Composable
private fun RoundList(
    rounds: List<RoundState>,
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth(),
        columns = GridCells.Fixed(2),
    ) {
        items(rounds) {

        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    TableGamesPointsSheetTheme {
        ChooseRoundPage(
            onRoundSelected = {},
            onNavigateBack = {}
        )
    }
}