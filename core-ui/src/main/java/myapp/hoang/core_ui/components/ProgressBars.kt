package myapp.hoang.core_ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import myapp.hoang.core_ui.LocalDimension

@Composable
fun OnBoardingProgressIndicator() {
    CircularProgressIndicator(
        color = MaterialTheme.colorScheme.onTertiaryContainer,
        strokeWidth = 3.dp,
        modifier = Modifier.size(LocalDimension.current.large),
    )
}