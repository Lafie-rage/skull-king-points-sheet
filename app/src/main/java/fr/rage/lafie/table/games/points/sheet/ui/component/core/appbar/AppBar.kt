package fr.rage.lafie.table.games.points.sheet.ui.component.core.appbar

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import fr.rage.lafie.table.games.points.sheet.R
import fr.rage.lafie.table.games.points.sheet.ui.component.core.appbar.menu.EndMatchMenuAction
import fr.rage.lafie.table.games.points.sheet.ui.component.core.appbar.menu.MatchRecapMenuAction
import fr.rage.lafie.table.games.points.sheet.ui.theme.TableGamesPointsSheetTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    actions: @Composable (RowScope.() -> Unit)? = null,
    onBackPressed: (() -> Unit)? = null,
) {
    var isMenuExpanded by remember { mutableStateOf(false) }

    BackHandler(enabled = onBackPressed != null) {
        onBackPressed!!.invoke()
    }

    TopAppBar(
        navigationIcon = {
            onBackPressed?.let {
                IconButton(
                    onClick = it
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(R.string.navigate_back)
                    )
                }
            }
        },
        actions = {
            actions?.let {
                IconButton(
                    onClick = { isMenuExpanded = !isMenuExpanded }
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = stringResource(R.string.display_action_menu),
                    )
                }

                DropdownMenu(
                    expanded = isMenuExpanded,
                    onDismissRequest = { isMenuExpanded = false }
                ) {
                    it
                }
            }
        },
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(title)
            }
        }
    )
}

@Preview
@Composable
fun Preview() {
    TableGamesPointsSheetTheme {
        AppBar(
            title = stringResource(R.string.app_name),
            actions = {
                EndMatchMenuAction { }
                MatchRecapMenuAction { }
            },
            onBackPressed = {},
        )
    }
}