package fr.rage.lafie.skull.king.points.sheet.ui.component

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.rage.lafie.skull.king.points.sheet.ui.theme.TableGamesPointsSheetTheme

@Composable
fun <T> ChooseItemList(
    items: List<T>,
    getItemLabel: (T) -> String,
    onItemSelected: (T) -> Unit,
    itemPerRow: Int = 2,
) {
    if (itemPerRow > 1) {
        GridList(
            items,
            getItemLabel,
            onItemSelected,
            itemPerRow
        )
    } else {
        SimpleList(
            items,
            getItemLabel,
            onItemSelected,
        )
    }
}

@Composable
private fun <T> GridList(
    items: List<T>,
    getItemLabel: (T) -> String,
    onItemSelected: (T) -> Unit,
    itemPerRow: Int = 2,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(itemPerRow),
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

@Composable
private fun <T> SimpleList(
    items: List<T>,
    getItemLabel: (T) -> String,
    onItemSelected: (T) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(items) { item ->
            ChooseItemButton(
                modifier = Modifier.fillMaxWidth(0.9f),
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
        ChooseItemList(
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