package fr.rage.lafie.skull.king.points.sheet.ui.page.match.choose

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.rage.lafie.skull.king.points.sheet.R
import fr.rage.lafie.skull.king.points.sheet.ui.component.ChooseItemList
import fr.rage.lafie.skull.king.points.sheet.ui.component.core.appbar.AppBar
import fr.rage.lafie.skull.king.points.sheet.ui.page.match.choose.state.MatchState
import fr.rage.lafie.skull.king.points.sheet.ui.theme.TableGamesPointsSheetTheme
import fr.rage.lafie.skull.king.points.sheet.utils.MapToComposable
import java.util.UUID

@Composable
fun ChooseMatchPage(
    onMatchSelected: (UUID) -> Unit,
    onCreateNewMatchClicked: () -> Unit,
    onBackPressed: () -> Unit,
    viewModel: ChooseMatchViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    state.MapToComposable(
        onSuccess = { (gameName, matches) ->
            Page(
                gameName = gameName,
                matches = matches,
                onNavigateToMatch = onMatchSelected,
                onCreateNewMatchClicked = onCreateNewMatchClicked,
                onBackPressed = onBackPressed,
            )
        }
    )
}

@Composable
private fun Page(
    gameName: String,
    matches: List<MatchState>,
    onNavigateToMatch: (UUID) -> Unit,
    onCreateNewMatchClicked: () -> Unit,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(R.string.choose_match_page_title, gameName),
                onBackPressed = onBackPressed,
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onCreateNewMatchClicked) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = stringResource(R.string.create_new_match)
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter,
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
            gameName = "Skull King",
            matches = listOf(
                MatchState(
                    id = UUID.randomUUID(),
                    name = "Match 1",
                    isFinished = false,
                ),
                MatchState(
                    id = UUID.randomUUID(),
                    name = "Match 2",
                    isFinished = false,
                ),
                MatchState(
                    id = UUID.randomUUID(),
                    name = "Match 3",
                    isFinished = false,
                ),
                MatchState(
                    id = UUID.randomUUID(),
                    name = "Match 4",
                    isFinished = false,
                )
            ),
            { matchId ->
                Toast.makeText(context, "Navigate to match with id $matchId", Toast.LENGTH_LONG)
                    .show()
            },
            {
                Toast.makeText(context, "Create new match FAB clicked", Toast.LENGTH_LONG)
                    .show()
            },
            {
                Toast.makeText(context, "Navigate back", Toast.LENGTH_LONG).show()
            }
        )
    }
}