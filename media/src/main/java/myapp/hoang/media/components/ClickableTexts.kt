package myapp.hoang.media.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import myapp.hoang.core_ui.ChevronDownIcon
import myapp.hoang.core_ui.LocalDimension

@Composable
fun MediaCollectionSelect(
    mediaCollection: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .wrapContentWidth()
            .fillMaxHeight()
            .clickable(onClick = onClick)
    ) {
        Text(
            text = mediaCollection,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.width(LocalDimension.current.small))
        ChevronDownIcon(
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(top = LocalDimension.current.extraSmall)
                .size(LocalDimension.current.mediumSmall)
        )
    }
}