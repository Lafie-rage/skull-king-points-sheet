package fr.rage.lafie.table.games.points.sheet.ui.component

import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.rage.lafie.table.games.points.sheet.ui.page.player.choose.PlayerState
import fr.rage.lafie.table.games.points.sheet.ui.theme.TableGamesPointsSheetTheme

@Composable
fun ChooseItemButton(
    label: String,
    onSelected: () -> Unit,
) {
    OutlinedButton(
        onClick = onSelected,
    ) {
        Text(text = label)
    }
}

@Composable
@Preview(showBackground = false)
fun ChoosePlayerButtonPreview() {
    TableGamesPointsSheetTheme {
        ChooseItemButton(
            label = "Player 1",
            onSelected = {},
        )
    }
}