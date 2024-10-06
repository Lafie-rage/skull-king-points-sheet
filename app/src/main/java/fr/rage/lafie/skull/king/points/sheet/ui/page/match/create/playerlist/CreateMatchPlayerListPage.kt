package fr.rage.lafie.skull.king.points.sheet.ui.page.match.create.playerlist

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.rage.lafie.skull.king.points.sheet.R
import fr.rage.lafie.skull.king.points.sheet.ui.component.core.appbar.AppBar
import fr.rage.lafie.skull.king.points.sheet.ui.theme.TableGamesPointsSheetTheme
import fr.rage.lafie.skull.king.points.sheet.utils.MapToComposable
import org.koin.androidx.compose.koinViewModel
import java.util.UUID

@Composable
fun CreateMatchPlayerListPage(
    onMatchCreated: (UUID) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: CreateMatchPlayerListViewModel = koinViewModel(),
) {
    val state by viewModel.state

    state.MapToComposable(
        onSuccess = { (title, matchId, playersName) ->
            Page(
                title = title,
                playersName = playersName,
                onChangePlayerName = viewModel::changePlayerName,
                onValidateMatch = {
                    viewModel.saveMatch()
                    onMatchCreated(matchId)
                },
                onBackPressed = onBackPressed,
            )
        }
    )
}

@Composable
private fun Page(
    title: String,
    playersName: List<String>,
    onChangePlayerName: (Int, String) -> Unit,
    onValidateMatch: () -> Unit,
    onBackPressed: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            AppBar(
                title = title,
                onBackPressed = onBackPressed,
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(0.9f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                itemsIndexed(playersName) { index, name ->
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        value = name,
                        onValueChange = { value ->
                            onChangePlayerName(index, value)
                        },
                        placeholder = {
                            Text(stringResource(R.string.player_X_name, index + 1))
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = if (index < playersName.size - 1) ImeAction.Next else ImeAction.Done,
                            capitalization = KeyboardCapitalization.Words,
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                keyboardController?.hide()
                                onValidateMatch()
                            }
                        ),
                    )
                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 8.dp),
                onClick = onValidateMatch,
                enabled = playersName.none { name -> name.isBlank() }
            ) {
                Text(stringResource(R.string.validate))
            }
        }
    }
}

@Preview
@Composable
private fun CreateMatchPlayerListPagePreview() {
    val context = LocalContext.current

    var playersName by remember {
        mutableStateOf(
            List(8) { "" }
        )
    }
    TableGamesPointsSheetTheme {
        Page(
            title = "Skull King - Match 1",
            playersName = playersName,
            onChangePlayerName = { index, name ->
                playersName = playersName.mapIndexed { i, baseValue ->
                    if (i == index) name else baseValue
                }
            },
            onValidateMatch = {
                Toast.makeText(context, "Create match", Toast.LENGTH_LONG).show()
            },
            onBackPressed = {
                Toast.makeText(context, "Navigate back", Toast.LENGTH_LONG).show()
            }
        )
    }
}