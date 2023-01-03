package myapp.hoang.instaclone.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable

sealed class MainScreenDrawer(
    val drawerContent: @Composable ColumnScope.() -> Unit
) {
    object NoDrawer : MainScreenDrawer({ PlaceholderDrawer() })
    object SelectAccountDrawer : MainScreenDrawer({ SelectAccountDrawer() })
}