package pl.fboro.taski.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.fboro.taski.R
import pl.fboro.taski.ui.theme.Typography

@Composable
fun TopPanel() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .align(Center),
            text = stringResource(R.string.app_name),
            style = Typography.h3
        )
        Image(
            modifier = Modifier.size(35.dp).align(CenterEnd),
            painter = painterResource(id = R.drawable.pen),
            contentDescription = stringResource(R.string.pen_icon_description),
        )
    }
}