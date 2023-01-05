package myapp.hoang.core_ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import myapp.hoang.core_ui.ChevronDownIcon
import myapp.hoang.core_ui.LinkBlue
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.White

@Composable
fun AlreadyHaveAccountClickableText(
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
        ClickableText(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = LinkBlue)) {
                    append("Already have an account?")
                }
            },
            style = MaterialTheme.typography.labelSmall,
            maxLines = 1,
            onClick = { onIsDialogShownChange(true) }
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

@Composable
fun PartiallyClickableText(
    unclickableText: String,
    clickableText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val annotatedText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = White)) {
            append(unclickableText)
        }
        append(" ")
        pushStringAnnotation(tag = "link", annotation = "link")
        withStyle(style = SpanStyle(color = LinkBlue)) {
            append(clickableText)
        }
        pop()
    }
    ClickableText(
        text = annotatedText,
        style = MaterialTheme.typography.labelMedium,
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = "link",
                start = offset,
                end = offset
            )
                .firstOrNull()?.let {
                    onClick()
                }
        },
        modifier = modifier
    )
}

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