package fr.rage.lafie.skull.king.points.sheet.ui.component.match.recap

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ColumnScope.MatchRecapCell(
    value: String = "",
    isHeader: Boolean = false,
) {
    Box(
        modifier = Modifier
            .border(1.dp, Color.Black)
            .weight(1f)
            .fillMaxWidth()
            .wrapContentWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            textAlign = TextAlign.Center,
            fontWeight = if (isHeader) FontWeight.Bold else FontWeight.Normal,
            text = value,
            style = if (isHeader) MaterialTheme.typography.headlineMedium else MaterialTheme.typography.bodyMedium
        )

    }
}

@Composable
fun ColumnScope.MatchRecapCell(
    value: Int,
    isHeader: Boolean = false,
) {
    MatchRecapCell(value.toString(), isHeader)
}

@Composable
fun ColumnScope.MatchRecapCell(
    value: Long,
    isHeader: Boolean = false,
) {
    MatchRecapCell(value.toString(), isHeader)
}

@Preview
@Composable
fun MatchRecapCellPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier.width(IntrinsicSize.Max),
        ) {
            MatchRecapCell()
            MatchRecapCell("Super texte archi méga long")
            MatchRecapCell("Super texte en gras", true)
            MatchRecapCell(1)
            MatchRecapCell(2, true)
            MatchRecapCell(123456789L)
            MatchRecapCell(987654321L, true)
        }
    }
}