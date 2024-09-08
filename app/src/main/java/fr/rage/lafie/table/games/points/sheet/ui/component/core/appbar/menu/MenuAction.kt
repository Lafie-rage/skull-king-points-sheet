package fr.rage.lafie.table.games.points.sheet.ui.component.core.appbar.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material.icons.outlined.TableView
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import fr.rage.lafie.table.games.points.sheet.R
import fr.rage.lafie.table.games.points.sheet.ui.theme.TableGamesPointsSheetTheme

@Composable
fun EndMatchMenuAction(
    onActionClicked: () -> Unit,
) {
    DropdownMenuItem(
        onClick = onActionClicked,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.CheckCircleOutline,
                contentDescription = stringResource(R.string.end_match)
            )
        },
        text = {
            Text(
                text = stringResource(R.string.end_match)
            )
        },
    )
}

@Composable
fun MatchRecapMenuAction(
    onActionClicked: () -> Unit,
) {
    DropdownMenuItem(
        onClick = onActionClicked,
        text = {
            Text(text = stringResource(R.string.see_match_recap))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.TableView,
                contentDescription = stringResource(R.string.see_match_recap)
            )
        },
    )
}

@Preview
@Composable
fun Preview() {
    TableGamesPointsSheetTheme {
        Column(Modifier.fillMaxWidth()) {
            EndMatchMenuAction { }
            MatchRecapMenuAction { }
        }
    }
}