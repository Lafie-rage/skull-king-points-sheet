package fr.rage.lafie.table.games.points.sheet.ui.page.match

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.rage.lafie.table.games.points.sheet.R
import fr.rage.lafie.table.games.points.sheet.ui.component.ChooseItemList
import fr.rage.lafie.table.games.points.sheet.ui.component.core.appbar.AppBar
import fr.rage.lafie.table.games.points.sheet.ui.page.match.state.MatchState
import fr.rage.lafie.table.games.points.sheet.ui.theme.TableGamesPointsSheetTheme
import fr.rage.lafie.table.games.points.sheet.utils.MapToComposable
import org.koin.compose.viewmodel.koinViewModel
import java.util.UUID

@Composable
fun ChooseMatchPage(
    onNavigateToMatch: (UUID) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: ChooseMatchViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    state.MapToComposable(
        onSuccess = { (gameName, matches) ->
            Page(
                gameName =,
                matches = matches,
                onNavigateToMatch = onNavigateToMatch,
                onNavigateBack = onNavigateBack,
            )
        }
    )
}

@Composable
private fun Page(
    gameName: String,
    matches: List<MatchState>,
    onNavigateToMatch: (UUID) -> Unit,
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(R.string.choose_match_page_title, gameName),
                onNavigateBack = onNavigateBack,
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            ChooseItemList(
                items = matches,
                getItemLabel = { match ->
                    match.name
                },
                onItemSelected = { match ->
                    onNavigateToMatch(match.id)
                },
                itemPerRow = 1,
            )
        }
    }
}

@Preview
@Composable
fun ChooseMatchPagePreview() {
    val context = LocalContext.current

    TableGamesPointsSheetTheme {
        Page(
            gameName ="Skull King",
            matches = listOf(
                MatchState(
                    id = UUID.randomUUID(),
                    name = "Match 1",
                ),
                MatchState(
                    id = UUID.randomUUID(),
                    name = ="Match 2",
                ),
                MatchState(
                    id = UUID.randomUUID(),
                    name = "Match 3",
                ),
                MatchState(
                    id = UUID.randomUUID(),
                    name = "Match 4",
                )
            ),
            { matchId ->
                Toast.makeText(context, "Navigate to match with id $matchId", Toast.LENGTH_LONG)
                    .show()
            },
            {
                Toast.makeText(context, "Navigate back", Toast.LENGTH_LONG).show()
            }
        )
    }
}