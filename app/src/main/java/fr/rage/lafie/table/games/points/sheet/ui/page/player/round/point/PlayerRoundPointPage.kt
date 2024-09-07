package fr.rage.lafie.table.games.points.sheet.ui.page.player.round.point

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import fr.rage.lafie.table.games.points.sheet.ui.component.AppBar
import fr.rage.lafie.table.games.points.sheet.ui.page.player.choose.PlayerState
import fr.rage.lafie.table.games.points.sheet.ui.routing.PlayerRoundPointRoute
import fr.rage.lafie.table.games.points.sheet.ui.theme.TableGamesPointsSheetTheme
import fr.rage.lafie.table.games.points.sheet.utils.process
import org.koin.compose.viewmodel.koinViewModel
import java.util.UUID
import kotlin.math.pow

@Composable
fun PlayerRoundPointPage(
    routeParams: PlayerRoundPointRoute,
    onNavigateBack: () -> Unit,
    viewModel: PlayerRoundPointViewModel = koinViewModel(),
) {

    val player by viewModel.player
    val points by viewModel.points

    LaunchedEffect(Unit) {
        viewModel.setPlayerId(UUID.fromString(routeParams.playerId))
    }

    player.process(onSuccess = {
        Page(
            player = it,
            points = points,
            onPointsChanged = viewModel::setPoints,
            onNavigateBack = onNavigateBack,
        )
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Page(
    player: PlayerState,
    points: Int,
    onPointsChanged: (Int) -> Unit,
    onNavigateBack: () -> Unit,
) {
    var selectedValue by remember { mutableIntStateOf(1) }

    Scaffold(
        topBar = {
            AppBar(
                title = player.name,
                onNavigateBack = onNavigateBack,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                repeat(3) { i ->
                    val value = (100 / 10.0.pow(i.toDouble())).toInt()
                    OutlinedIconButton(
                        onClick = {
                            selectedValue = value
                        },
                        content = {
                            Text(value.toString())
                        },
                        enabled = value != selectedValue
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                OutlinedIconButton(
                    onClick = {},
                    content = {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "remove", // TODO Translate
                        )
                    }
                )
                TextField(
                    value = points.toString(),
                    onValueChange = {
                        onPointsChanged(it.toInt())
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
                OutlinedIconButton(
                    onClick = {},
                    content = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "add", // TODO Translate
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    TableGamesPointsSheetTheme {
        var points by remember {
            mutableIntStateOf(0)
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
            onNavigateBack = {}
        )
    }
}