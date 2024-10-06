package fr.rage.lafie.skull.king.points.sheet.ui.page.match.recap

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.rage.lafie.skull.king.points.sheet.R
import fr.rage.lafie.skull.king.points.sheet.ui.component.core.appbar.AppBar
import fr.rage.lafie.skull.king.points.sheet.ui.component.match.recap.MatchRecapCell
import fr.rage.lafie.skull.king.points.sheet.ui.page.match.recap.state.PlayerAndScoreState
import fr.rage.lafie.skull.king.points.sheet.ui.theme.TableGamesPointsSheetTheme
import fr.rage.lafie.skull.king.points.sheet.utils.MapToComposable
import org.koin.androidx.compose.koinViewModel

@Composable
fun MatchRecapPage(
    onBackPressed: () -> Unit,
    viewModel: MatchRecapViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    state.MapToComposable(
        onSuccess = { (title, players) ->
            Page(
                title = title,
                players = players,
                onBackPressed = onBackPressed,
            )
        }
    )
}

@Composable
private fun Page(
    title: String,
    players: List<PlayerAndScoreState>,
    onBackPressed: () -> Unit,
) {
    val roundCount = players.map(PlayerAndScoreState::roundScores)
        .maxOf(List<Long>::size)

    Scaffold(topBar = {
        AppBar(
            title = title,
            onBackPressed = onBackPressed,
        )
    }) { innerPadding ->
        LazyRow(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            item {
                Column(
                    modifier = Modifier.width(IntrinsicSize.Max),
                ) {
                    // Round indexes
                    MatchRecapCell()
                    repeat(roundCount) { roundIndex ->
                        MatchRecapCell(roundIndex + 1, true)
                    }
                    MatchRecapCell(stringResource(R.string.total), true)
                }
            }
            // Players score
            items(players) { player ->
                Column(
                    modifier = Modifier.width(IntrinsicSize.Max),
                ) {
                    val missingRoundsCount = maxOf(roundCount - player.roundScores.size, 0)
                    MatchRecapCell(player.name, true)
                    player.roundScores.map { score ->
                        MatchRecapCell(score)
                    }
                    repeat(missingRoundsCount) {
                        MatchRecapCell("X")
                    }
                    MatchRecapCell(player.totalScore, true)
                }
            }
        }
    }
}


@Preview
@Composable
fun MatchRecapPagePreview() {
    val context = LocalContext.current

    val players = listOf(
        PlayerAndScoreState(
            name = "John Doe",
            roundScores = listOf(
                10,
                -20,
                30,
                60,
                -50,
                -60,
                -70,
                120,
                80,
                -100,
            ),

            ), PlayerAndScoreState(
            name = "Maria Database", roundScores = listOf(
                10,
                20,
                30,
                40,
                50,
                60,
                70,
                80,
                90,
                100,
            )
        )
    )

    TableGamesPointsSheetTheme {
        Page(
            title = "Skull King - Match 1",
            players = players,
            onBackPressed = {
                Toast.makeText(context, "Back button pressed", Toast.LENGTH_LONG).show()
            },
        )
    }
}
