package fr.rage.lafie.table.games.points.sheet.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.rage.lafie.table.games.points.sheet.R
import fr.rage.lafie.table.games.points.sheet.ui.theme.TableGamesPointsSheetTheme

@Composable
fun PlayerPointKeyboard(
    points: Long,
    onPointsChanged: (Long) -> Unit,
) {
    var selectedValue by remember { mutableIntStateOf(0) }

    val itemModifier = Modifier
        .padding(8.dp)
        .aspectRatio(1f)

    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(listOf(1, 10, 100)) { value ->
                OutlinedIconButton(
                    modifier = itemModifier,
                    onClick = {
                        selectedValue = value
                    },
                    content = {
                        Text(value.toString())
                    },
                    enabled = value != selectedValue
                )
            }
            item {
                OutlinedIconButton(
                    modifier = itemModifier,
                    onClick = {
                        onPointsChanged(points - selectedValue)
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = stringResource(R.string.decrease_points),
                        )
                    }
                )
            }
            item {
                OutlinedIconButton(
                    modifier = itemModifier,
                    onClick = {
                        onPointsChanged(0)
                    },
                    content = {
                        Text("0")
                    }
                )
            }
            item {
                OutlinedIconButton(
                    modifier = itemModifier,
                    onClick = {
                        onPointsChanged(points + selectedValue)
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(R.string.increase_points),
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
        var points by remember { mutableLongStateOf(0) }

        PlayerPointKeyboard(
            points = points,
            onPointsChanged = { points = it },
        )
    }
}