package fr.rage.lafie.table.games.points.sheet.ui.component

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.rage.lafie.table.games.points.sheet.ui.page.player.choose.PlayerState
import fr.rage.lafie.table.games.points.sheet.ui.theme.TableGamesPointsSheetTheme
import java.util.UUID

@Composable
fun ChoosePlayerButton(
    player: PlayerState,
    onNavigateToPlayer: (PlayerState) -> Unit,
) {
    OutlinedButton(
        onClick = { onNavigateToPlayer(player) },
    ) {
        Text(text = player.name)
    }
}

@Composable
@Preview(showBackground = false)
fun ChoosePlayerButtonPreview() {
    TableGamesPointsSheetTheme {
        ChoosePlayerButton(
            player = PlayerState(
                id = UUID.randomUUID(),
                name = "Player 1",
            ),
            onNavigateToPlayer = {}
        )
    }
}