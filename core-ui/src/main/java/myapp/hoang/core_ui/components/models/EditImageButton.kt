package myapp.hoang.core_ui.components.models

import androidx.compose.runtime.Composable

data class EditImageButton(
    val text: String,
    val icon: @Composable () -> Unit
)