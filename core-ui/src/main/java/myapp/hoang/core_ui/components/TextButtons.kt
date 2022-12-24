package myapp.hoang.core_ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import myapp.hoang.core_ui.LinkBlue

@Composable
fun AlreadyHaveAccountTextButton(
    isDialogShown: Boolean,
    onIsDialogShownChange: (Boolean) -> Unit,
    onBackClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = "Already have an account?",
            color = LinkBlue,
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.clickable { onIsDialogShownChange(true) }
        )
    }
    if (isDialogShown) {
        AlreadyHaveAccountDialog(
            onConfirm = {
                onIsDialogShownChange(false)
                onBackClick()
                onBackClick()
            },
            onDismiss = { onIsDialogShownChange(false) }
        )
    }
}