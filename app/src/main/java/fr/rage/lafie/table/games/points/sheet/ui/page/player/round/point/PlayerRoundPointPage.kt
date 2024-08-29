package fr.rage.lafie.table.games.points.sheet.ui.page.player.round.point

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import fr.rage.lafie.table.games.points.sheet.R
import fr.rage.lafie.table.games.points.sheet.ui.page.player.choose.PlayerState
import fr.rage.lafie.table.games.points.sheet.ui.routing.PlayerRoundPointRoute
import fr.rage.lafie.table.games.points.sheet.ui.theme.TableGamesPointsSheetTheme
import fr.rage.lafie.table.games.points.sheet.utils.process
import org.koin.compose.viewmodel.koinViewModel
import java.util.UUID

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

@Composable
private fun Page(
    player: PlayerState,
    points: Int,
    onPointsChanged: (Int) -> Unit,
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.navigate_back),
                    )
                }
                Text(text = player.name)
            }

        }
    ) { innerPadding ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            TextField(
                value = points.toString(),
                onValueChange = {
                    onPointsChanged(it.toInt())
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
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