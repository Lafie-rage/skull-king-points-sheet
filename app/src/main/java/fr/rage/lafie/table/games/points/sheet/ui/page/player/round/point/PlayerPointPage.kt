package fr.rage.lafie.table.games.points.sheet.ui.page.player.round.point

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.rage.lafie.table.games.points.sheet.R
import fr.rage.lafie.table.games.points.sheet.ui.component.PlayerPointKeyboard
import fr.rage.lafie.table.games.points.sheet.ui.component.core.appbar.AppBar
import fr.rage.lafie.table.games.points.sheet.ui.page.player.choose.PlayerState
import fr.rage.lafie.table.games.points.sheet.ui.theme.TableGamesPointsSheetTheme
import fr.rage.lafie.table.games.points.sheet.utils.MapToComposable
import org.koin.compose.viewmodel.koinViewModel
import java.util.UUID

@Composable
fun PlayerPointPage(
    onBackPressed: () -> Unit,
    viewModel: PlayerPointViewModel = koinViewModel(),
) {

    val player by viewModel.player
    val points by viewModel.points

    player.MapToComposable(onSuccess = {
        Page(
            player = it,
            points = points,
            onPointsChanged = viewModel::setPoints,
            onValidatePressed = {
                viewModel.save()
                onBackPressed()
            },
            onBackPressed = onBackPressed,
        )
    })
}

@Composable
private fun Page(
    player: PlayerState,
    points: Long,
    onPointsChanged: (Long) -> Unit,
    onValidatePressed: () -> Unit,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppBar(
                title = player.name,
                onBackPressed = onBackPressed,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                TextField(
                    value = points.toString(),
                    onValueChange = {
                        onPointsChanged(it.toLong())
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
            }
            PlayerPointKeyboard(
                points = points,
                onPointsChanged = onPointsChanged,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    onClick = onValidatePressed,
                ) {
                    Text(stringResource(R.string.validate))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    TableGamesPointsSheetTheme {
        var points by remember {
            mutableLongStateOf(0)
        }
        Page(
            player = PlayerState(
                id = UUID.randomUUID(),
                name = "Player 1"
            ),
            points = points,
            onPointsChanged = {
                points = it
            },
            onValidatePressed = {},
            onBackPressed = {},
        )
    }
}