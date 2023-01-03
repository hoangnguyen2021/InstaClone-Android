package myapp.hoang.instaclone.components

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
fun ProfileUsername(
    username: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Text(
            text = username,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.width(LocalDimension.current.extraSmall))
        ChevronDownIcon(
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(top = LocalDimension.current.extraSmall)
                .size(LocalDimension.current.mediumSmall)
        )
    }
}