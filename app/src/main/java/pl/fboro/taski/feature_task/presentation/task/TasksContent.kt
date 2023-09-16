package pl.fboro.taski.feature_task.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TasksContent() {
    var category by remember{ mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .padding(30.dp)
                .clickable {
                    if (category < 2) category += 1 else category = 0
                },
            text = when (category){
            0 -> "Nadchodzące"
            1 -> "Zrobione"
            2 -> "Wszystkie"
            else -> "Błąd"
        })
        Column() {
            
        }
    }


}