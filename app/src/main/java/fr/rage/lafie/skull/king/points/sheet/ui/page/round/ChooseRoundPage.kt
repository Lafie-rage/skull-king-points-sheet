package fr.rage.lafie.skull.king.points.sheet.ui.page.round

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.TableChart
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import fr.rage.lafie.skull.king.points.sheet.R
import fr.rage.lafie.skull.king.points.sheet.ui.component.ChooseItemList
import fr.rage.lafie.skull.king.points.sheet.ui.component.core.appbar.AppBar
import fr.rage.lafie.skull.king.points.sheet.ui.page.round.state.RoundState
import fr.rage.lafie.skull.king.points.sheet.ui.theme.TableGamesPointsSheetTheme
import fr.rage.lafie.skull.king.points.sheet.utils.MapToComposable

@Composable
fun ChooseRoundPage(
    onRoundSelected: (Int) -> Unit,
    navigateToMatchRecap: () -> Unit,
    onBackPressed: () -> Unit,
    viewModel: ChooseRoundViewModel = hiltViewModel(),
) {
    val state by viewModel.state

    state.MapToComposable(
        onSuccess = {
            Page(
                title = it.title,
                maxRound = it.maxRound,
                isMatchFinished = it.isMatchFinished,
                rounds = it.rounds,
                createNewRound = viewModel::createNewRound,
                finishMatch = viewModel::finishMatch,
                navigateToRound = onRoundSelected,
                navigateToMatchRecap = navigateToMatchRecap,
                onBackPressed = onBackPressed,
            )
        }
    )
}

@Composable
private fun Page(
    title: String,
    maxRound: Int,
    isMatchFinished: Boolean,
    rounds: List<RoundState>,
    createNewRound: () -> Unit,
    finishMatch: () -> Unit,
    navigateToMatchRecap: () -> Unit,
    navigateToRound: (Int) -> Unit,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppBar(
                title = title,
                onBackPressed = onBackPressed,
                actions = {
                    ActionsMenu(
                        finishMatch = finishMatch,
                        navigateToMatchRecap = navigateToMatchRecap,
                    )
                }
            )
        },
        floatingActionButton = {
            if (maxRound > rounds.size && !isMatchFinished) {
                FloatingActionButton(onClick = createNewRound) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = stringResource(R.string.create_new_round)
                    )
                }
            }
        },

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
                onItemSelected = navigateToRound,
            )
        }
    }
}

@Composable
private fun ActionsMenu(
    finishMatch: () -> Unit,
    navigateToMatchRecap: () -> Unit,
) {
    var showMenu by remember { mutableStateOf(false) }

    IconButton(onClick = { showMenu = !showMenu }) {
        Icon(
            Icons.Default.MoreVert,
            contentDescription = stringResource(R.string.show_more_actions)
        )
    }
    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = { showMenu = false }
    ) {
        DropdownMenuItem(
            onClick = finishMatch,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = stringResource(R.string.end_match)
                )
            },
            text = {
                Text(stringResource(R.string.end_match))
            }
        )
        DropdownMenuItem(
            onClick = navigateToMatchRecap,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.TableChart,
                    contentDescription = stringResource(R.string.show_match_recap)
                )
            },
            text = {
                Text(stringResource(R.string.recap))
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChooseRoundPagePreview() {
    val context = LocalContext.current

    TableGamesPointsSheetTheme {
        Page(
            title = "Skull King - Match 1",
            maxRound = 4,
            isMatchFinished = false,
            rounds = listOf(
                RoundState(1),
                RoundState(2),
                RoundState(3),
                RoundState(4),
            ),
            createNewRound = {
                Toast.makeText(context, "Create a new round", Toast.LENGTH_LONG).show()
            },
            finishMatch = {
                Toast.makeText(context, "Finish match", Toast.LENGTH_LONG).show()
            },
            navigateToRound = { roundIndex ->
                Toast.makeText(context, "Navigate to round $roundIndex", Toast.LENGTH_LONG).show()
            },
            navigateToMatchRecap = {
                Toast.makeText(context, "Navigate to match recap", Toast.LENGTH_LONG).show()
            },
            onBackPressed = {
                Toast.makeText(context, "Navigate back", Toast.LENGTH_LONG).show()
            }
        )
    }
}