package myapp.hoang.core_ui.components.models

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import myapp.hoang.core_ui.components.PlaceholderDrawer
import myapp.hoang.core_ui.components.SelectAccountDrawer

sealed class MainScreenDrawer(
    val drawerContent: @Composable ColumnScope.() -> Unit
) {
    object NoDrawer : MainScreenDrawer({ PlaceholderDrawer() })
    object SelectAccountDrawer : MainScreenDrawer({ SelectAccountDrawer() })
}