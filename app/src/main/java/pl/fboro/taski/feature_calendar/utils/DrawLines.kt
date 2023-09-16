package pl.fboro.taski.feature_calendar

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DrawLines(row: Int, column: Int) {

    if (column > 0 && row == 0) {
        Divider(
            color = Color.Black,
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
                .padding(top = 20.dp),
        )
    }
    if (column > 0 && row == 6) {
        Divider(
            color = Color.Black,
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
                .padding(bottom = 10.dp)
        )
    }
    if (column > 0 && row > 0 && row < 6) {
        Divider(
            color = Color.Black,
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp))
    }
    if (row > 0 && column == 0) {
        Divider(
            color = Color.Black,
            thickness = 2.dp,
            startIndent = 25.dp
        )
    }
    if (row > 0 && column > 0 && column < 8) {
        Divider(
            color = Color.Black,
            thickness = 2.dp,
        )
    }
    if (row > 0 && column == 8) {
        Divider(
            modifier = Modifier.padding(end = 25.dp),
            color = Color.Black,
            thickness = 2.dp
        )
    }
}