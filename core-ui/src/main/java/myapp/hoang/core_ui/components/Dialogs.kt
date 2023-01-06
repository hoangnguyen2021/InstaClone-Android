package myapp.hoang.core_ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import myapp.hoang.core_ui.*
import java.time.LocalDate

@Composable
fun AlertDialog(
    title: String,
    body: String,
    dismissText: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1
            )
        },
        text = {
            Text(
                text = body,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {},
        dismissButton = {
            DialogButton(
                text = dismissText,
                onClick = onDismiss
            )
        },
        shape = RoundedCornerShape(LocalDimension.current.small),
        containerColor = Charcoal,
        titleContentColor = MaterialTheme.colorScheme.onSurface,
        textContentColor = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .wrapContentHeight()
    )
}

@Composable
fun AlertDialog(
    title: String,
    body: String,
    confirmText: String,
    dismissText: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1
            )
        },
        text = {
            Text(
                text = body,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {
            DialogButton(
                text = confirmText,
                onClick = onConfirm
            )
        },
        dismissButton = {
            DialogButton(
                text = dismissText,
                onClick = onDismiss
            )
        },
        shape = RoundedCornerShape(LocalDimension.current.small),
        containerColor = Charcoal,
        titleContentColor = MaterialTheme.colorScheme.onSurface,
        textContentColor = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .wrapContentHeight()
    )
}

@Composable
fun AlreadyHaveAccountDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Already have an account?",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1
            )
        },
        confirmButton = {
            DialogButton(
                text = "Log in",
                onClick = onConfirm
            )
        },
        dismissButton = {
            DialogButton(
                text = "Continue creating account",
                onClick = onDismiss
            )
        },
        shape = RoundedCornerShape(LocalDimension.current.small),
        containerColor = Charcoal,
        titleContentColor = MaterialTheme.colorScheme.onSurface,
        textContentColor = MaterialTheme.colorScheme.onSurface,
    )
}

@Composable
fun DialogButton(
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .wrapContentSize()
    ) {
        Text(
            text = text,
            color = White,
            style = MaterialTheme.typography.displayMedium,
            maxLines = 1
        )
    }
}

@Composable
fun DatePickerDialog(
    label: String,
    onDismiss: () -> Unit,
    textFieldValue: LocalDate,
    onTextFieldValueChange: (LocalDate) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        DatePicker(
            label = label,
            onDismiss = onDismiss,
            textFieldValue = textFieldValue,
            onTextFieldValueChange = onTextFieldValueChange
        )
    }
}