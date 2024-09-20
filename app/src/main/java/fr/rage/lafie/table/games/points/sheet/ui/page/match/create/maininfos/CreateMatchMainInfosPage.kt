package fr.rage.lafie.table.games.points.sheet.ui.page.match.create.maininfos

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import fr.rage.lafie.table.games.points.sheet.ui.theme.TableGamesPointsSheetTheme
import fr.rage.lafie.table.games.points.sheet.utils.MapToComposable
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateMatchMainInfosPage(
    onNextButtonClicked: () -> Unit,
    onBackPressed: () -> Unit,
    viewModel: CreateMatchMainInfosViewModel = koinViewModel(),
) {
    val state by viewModel.state

    state.MapToComposable(
        onSuccess = {
            Page(
                matchName = it.matchName,
                playersCount = it.playersCount,
                minPlayers = it.minPlayers,
                maxPlayers = it.maxPlayers,
                onMatchNameChanged = viewModel::changeMatchName,
                onPlayersCountIncrease = viewModel::increaseMaxPlayer,
                onPlayersCountDecrease = viewModel::decreaseMaxPlayer,
                onNextButtonClicked = onNextButtonClicked,
                onBackPressed = onBackPressed,
            )
        }
    )
}

@Composable
private fun Page(
    matchName: String,
    playersCount: Int,
    minPlayers: Int,
    maxPlayers: Int,
    onMatchNameChanged: (String) -> Unit,
    onPlayersCountIncrease: () -> Unit,
    onPlayersCountDecrease: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onBackPressed: () -> Unit,
) {

}

@Preview
@Composable
fun CreateMatchMainInfosPagePreview() {
    val context = LocalContext.current

    var matchName by remember { mutableStateOf("") }
    var playersCount by remember { mutableIntStateOf(0) }
    TableGamesPointsSheetTheme {
        Page(
            matchName = matchName,
            playersCount = playersCount,
            minPlayers = 1,
            maxPlayers = 8,
            onMatchNameChanged = { value -> matchName = value },
            onPlayersCountIncrease = { playersCount += 1 },
            onPlayersCountDecrease = { playersCount -= 1 },
            onNextButtonClicked = {}, // TODO Toast
            onBackPressed = {}, // TODO Toast
        )
    }
}