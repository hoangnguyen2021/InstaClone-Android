package myapp.hoang.core_ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import myapp.hoang.core_ui.LinkBlue
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
    modifier: Modifier = Modifier,
    onClick: () -> Unit
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