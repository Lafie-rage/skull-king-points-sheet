package fr.rage.lafie.skull.king.points.sheet.ui.page.match.create.maininfos

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.rage.lafie.skull.king.points.sheet.R
import fr.rage.lafie.skull.king.points.sheet.ui.component.core.appbar.AppBar
import fr.rage.lafie.skull.king.points.sheet.ui.theme.TableGamesPointsSheetTheme
import fr.rage.lafie.skull.king.points.sheet.utils.MapToComposable

@Composable
fun CreateMatchMainInfosPage(
    onNavigateToNextPage: (String, Int) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: CreateMatchMainInfosViewModel = hiltViewModel(),
) {
    val state by viewModel.state

    state.MapToComposable(onSuccess = {
        Page(
            matchName = it.matchName,
            minPlayers = it.minPlayers,
            maxPlayers = it.maxPlayers,
            onMatchNameChanged = viewModel::changeMatchName,
            onNextButtonClicked = onNavigateToNextPage,
            onBackPressed = onBackPressed,
        )
    })
}

@Composable
private fun Page(
    matchName: String,
    minPlayers: Int,
    maxPlayers: Int,
    onMatchNameChanged: (String) -> Unit,
    onNextButtonClicked: (String, Int) -> Unit,
    onBackPressed: () -> Unit,
) {
    var playersCountValue by remember { mutableStateOf(minPlayers.toString()) }
    val playersCount = playersCountValue.toIntOrNull() ?: 0
    val isPlayersCountAboveMax = playersCount > maxPlayers
    val isPlayersCountUnderMin = playersCount < minPlayers
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(topBar = {
        AppBar(
            title = stringResource(R.string.start_new_match),
            onBackPressed = onBackPressed,
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.9f),
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(stringResource(R.string.match_name))
                    },
                    value = matchName, onValueChange = onMatchNameChanged,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.Sentences,
                    ),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedIconButton(
                        onClick = { playersCountValue = (playersCount - 1).toString() },
                        enabled = playersCount > minPlayers,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = stringResource(R.string.decrease_players_count)
                        )
                    }
                    TextField(modifier = Modifier.fillMaxWidth(0.7f),
                        value = playersCountValue,
                        onValueChange = {
                            playersCountValue = it
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done,
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                keyboardController?.hide()
                                onNextButtonClicked(matchName, playersCount)
                            }
                        ),
                        isError = isPlayersCountAboveMax || isPlayersCountUnderMin,
                        supportingText = {
                            if (isPlayersCountUnderMin) {
                                Text(
                                    stringResource(
                                        R.string.cannot_have_less_than_X_players, minPlayers
                                    )
                                )
                            }
                            if (isPlayersCountAboveMax) {
                                Text(
                                    stringResource(
                                        R.string.cannot_have_more_than_X_players, maxPlayers
                                    )
                                )
                            }
                        })
                    OutlinedIconButton(
                        onClick = { playersCountValue = (playersCount + 1).toString() },
                        enabled = playersCount < maxPlayers,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(R.string.increase_players_count)
                        )
                    }
                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 8.dp),
                onClick = {
                    onNextButtonClicked(matchName, playersCount)
                },
                enabled = !(isPlayersCountUnderMin || isPlayersCountAboveMax || matchName.isBlank())
            ) {
                Text(stringResource(R.string.next))
            }
        }
    }
}

@Preview
@Composable
fun CreateMatchMainInfosPagePreview() {
    val context = LocalContext.current

    var matchName by remember { mutableStateOf("") }
    TableGamesPointsSheetTheme {
        Page(
            matchName = matchName,
            minPlayers = 1,
            maxPlayers = 8,
            onMatchNameChanged = { value -> matchName = value },
            onNextButtonClicked = { _, _ ->
                Toast.makeText(context, "Navigate to next page", Toast.LENGTH_LONG).show()
            },
            onBackPressed = {
                Toast.makeText(context, "Navigate back", Toast.LENGTH_LONG).show()
            },
        )
    }
}