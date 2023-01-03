package myapp.hoang.core_ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FeedDivider() {
    Divider(
        color = MaterialTheme.colorScheme.outline,
        thickness = 0.3.dp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
}