package fr.rage.lafie.table.games.points.sheet.ui.component

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.rage.lafie.table.games.points.sheet.ui.theme.TableGamesPointsSheetTheme

@Composable
fun <T> ChooseItemGridList(
    items: List<T>,
    getItemLabel: (T) -> String,
    onItemSelected: (T) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(items) { item ->
            ChooseItemButton(
                label = getItemLabel(item),
                onSelected = { onItemSelected(item) },
            )
        }
    }
}

@Preview
@Composable
fun ChooseItemGridPreview() {
    val context = LocalContext.current

    TableGamesPointsSheetTheme {
        ChooseItemGridList(
            items = listOf(
                "Item 1",
                "Item 2",
                "Item 3",
                "Item 4",
            ),
            getItemLabel = { it },
            onItemSelected = {
                Toast.makeText(context, "Item selected : $it", Toast.LENGTH_LONG).show()
            },
        )
    }
}