package pl.fboro.taski.feature_task.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun TransparentTextField(
    value: String,
    placeholder: String,
    singleLine: Boolean,
    imeAction: ImeAction,
    changeValue: (String) -> Unit,
) {

    OutlinedTextField(
        value = value,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Gray,
        ),
        onValueChange = {
            changeValue(it)
        },
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = imeAction,
        ),
        placeholder = { Text(text = placeholder)}
    )
}